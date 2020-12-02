package com.thekdub.miniworld;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileDeleteStrategy;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MiniWorldAPI {

  /**
   * Generate an 'empty' Minecraft world.
   * Includes a 32x32x1 pad of bedrock centered on 0, 0, 0 for building off of.
   * Initial spawn location is 0, 3, 0.
   * @param name the desired name for the world. Will be prefixed by 'mw/' automatically if not present.
   * @return an instance of the new world.
   */
  public static World createEmptyWorld(String name) {
    World world = new MiniWorldCreator(name).createWorld();
    assert world != null;
    world.setSpawnLocation(0, 3, 0);
    for (int x = -16; x < 16; x++) {
      for (int z = -16; z < 16; z++) {
        world.getBlockAt(x,0,z).setType(Material.BEDROCK);
      }
    }
    return world;
  }

  /**
   * Creates a clone of an existing world.
   * Clone world name will be (parent_world_name)/clone/(clone_id)
   * @param world the world you wish to clone.
   * @return an instance of the new world.
   */
  public static World createCloneOf(World world) {
    return MiniWorldCloner.clone(world);
  }

  /**
   * Unloads and deletes a world and any existing clones of the world from the server.
   * Note: Will not delete worlds that were not created by MiniWorld.
   * @param world the world you wish to delete.
   */
  public static void deleteWorld(World world) {
    if (!world.getName().startsWith("mw/")) {
      return;
    }
    if (world.getName().contains("/clone/")) {
      deleteCloneWorld(world);
      return;
    }
    for (World w : Bukkit.getWorlds()) { // Deletes all 'sub-worlds' of the world being deleted, if any.
      if (w.getName().startsWith(world.getName()) && !world.getName().equalsIgnoreCase(w.getName())) {
        deleteCloneWorld(w);
      }
    }
    for (Player player : world.getPlayers()) {
      player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation(),
            PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
    Bukkit.unloadWorld(world, false);
    File folder = world.getWorldFolder();
    while (folder.getParentFile().listFiles().length == 1) {
      folder = folder.getParentFile();
    }
    if (!folder.delete()) {
      try {
        FileDeleteStrategy.FORCE.delete(folder);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Unloads and deletes a clone of a world from the server.
   * Note: Will not delete worlds that were not created by MiniWorld.
   * @param world the cloned world you wish to delete.
   */
  public static void deleteCloneWorld(World world) {
    if (!world.getName().startsWith("mw/")) {
      return;
    }
    if (!world.getName().contains("/clone/")) {
      deleteWorld(world);
      return;
    }
    for (Player player : world.getPlayers()) {
      player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation(),
            PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
    Bukkit.unloadWorld(world, false);
    File folder = world.getWorldFolder();
    if (!folder.delete()) {
      try {
        FileDeleteStrategy.FORCE.delete(folder);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
