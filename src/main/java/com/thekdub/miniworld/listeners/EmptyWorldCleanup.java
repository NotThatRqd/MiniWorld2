package com.thekdub.miniworld.listeners;

import com.thekdub.miniworld.MiniWorld;
import com.thekdub.miniworld.MiniWorldAPI;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class EmptyWorldCleanup implements Listener {

  /**
   * Automatically removes empty clone worlds from the server once they're no longer occupied.
   * @param e the PlayerTeleportEvent
   */
  @EventHandler
  public void onPlayerTeleport(PlayerTeleportEvent e) {
    World world = e.getFrom().getWorld();
    if (world == null) {
      return;
    }
    if (world.getName().startsWith("mw/") && world.getName().contains("/clone/")) {
      new BukkitRunnable() {
        public void run() {
          if (world.getPlayers().size() == 0) {
            MiniWorldAPI.deleteCloneWorld(world);
          }
        }
      }.runTaskLater(MiniWorld.getInstance(), 20);
    }

  }

}
