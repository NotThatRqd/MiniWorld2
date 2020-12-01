package com.thekdub.miniworld.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class ReturnToOverworld implements Listener {

  /**
   * Automatically returns a user to the overworld before disconnecting from the server when in a clone of a world.
   * @param e the PlayerQuitEvent.
   */
  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    if (e.getPlayer().getWorld().getName().contains("/clone/")) {
      e.getPlayer().teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }
  }

  /**
   * Automatically returns a user to the overworld before disconnecting from the server when in a clone of a world.
   * @param e the PlayerKickEvent.
   */
  @EventHandler
  public void onKick(PlayerKickEvent e) {
    if (e.getPlayer().getWorld().getName().contains("/clone/")) {
      e.getPlayer().teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }
  }
}
