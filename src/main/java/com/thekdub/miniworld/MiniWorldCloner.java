package com.thekdub.miniworld;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileDeleteStrategy;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.FileUtil;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MiniWorldCloner {

  private static final HashMap<World, Integer> cloneNums = new HashMap<>();

  /**
   * Creates a clone of an existing Minecraft world
   * @param world the world you want to clone.
   * @return the instance of the world clone.
   */
  protected static World clone(World world) {
    ((CraftWorld)world).getHandle().getMinecraftWorld().save(null, true, false);
    File worldFolder = world.getWorldFolder();
    int cloneNum = cloneNums.getOrDefault(world, 0);
    cloneNums.put(world, cloneNum+1);
    explore(worldFolder, worldFolder, cloneNum);
    return new MiniWorldCreator(world.getName() + "/clone/" + cloneNum).copy(world).createWorld();
  }

  /**
   * Explores a directory tree recursively; copies files as discovered.
   * @param parent the parent directory to reference from.
   * @param file initially, the parent directory; later, the current directory to explore.
   * @param cloneNum the clone id number.
   */
  private static void explore(@Nonnull File parent, @Nonnull File file, int cloneNum) {
    if (file.isDirectory()) {
      for (File f : file.listFiles()) {
        if (!f.getName().equalsIgnoreCase("temp")) {
          explore(parent, f, cloneNum);
        }
      }
    }
    else {
      copy(parent, file, cloneNum);
    }
  }

  /**
   * Copies a file from the parent world to the clone world directory.
   * @param parent the parent directory to reference from.
   * @param file the file to be cloned.
   * @param cloneNum the clone id number.
   */
  private static void copy(@Nonnull File parent, @Nonnull File file, int cloneNum) {
    if (file.getName().equalsIgnoreCase("uid.dat")) {
      return;
    }
    String newPath = parent.getAbsolutePath() + File.separator + "clone" + File.separator + cloneNum +
          file.getAbsolutePath().substring(parent.getAbsolutePath().length());
    File newFile = new File(newPath);
    newFile.getParentFile().mkdirs();
    FileUtil.copy(file, newFile);
  }

  /**
   * Cleans the server of all existing cloned worlds. Unloads and deletes all clone world files.
   */
  protected static void clean() {
    for (World world : Bukkit.getWorlds()) {
      if (world.getName().contains("/clone/")) {
        MiniWorldAPI.deleteCloneWorld(world);
      }
    }
    File mw = new File("mw");
    if (!mw.exists()) {
      return;
    }
    new BukkitRunnable() {
      public void run() {
        for (File file : mw.listFiles()) {
          if (file.isDirectory()) {
            for (File f : file.listFiles()) {
              if (f.getName().equalsIgnoreCase("clone")) {
                if (!f.delete()) {
                  try {
                    FileDeleteStrategy.FORCE.delete(f);
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                }
              }
            }
          }
        }
      }
    }.runTaskAsynchronously(MiniWorld.getInstance());
  }

}
