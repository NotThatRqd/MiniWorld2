package com.thekdub.miniworld.listeners;

import net.minecraft.server.v1_16_R3.EntityInsentient;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Objects;

public class StopEntityMovement implements Listener {

  /**
   * Prevents entities from moving while in a non-clone of a MiniWorld.
   * TODO: Figure out a way to make this work without attributes carrying over to clones of the world.
   * @param e the EntitySpawnEvent.
   */
  @EventHandler
  public void entitySpawn(EntitySpawnEvent e) {
    if (e.getEntity() instanceof LivingEntity) {
      if (e.getEntity().getWorld().getName().startsWith("mw/")
            && !e.getEntity().getWorld().getName().contains("/clone/")) {
        CraftLivingEntity cle = (CraftLivingEntity) e.getEntity();
        AttributeInstance attribute = cle.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if (attribute != null) {
          attribute.setBaseValue(0.0);
        }
        attribute = cle.getAttribute(Attribute.GENERIC_FLYING_SPEED);
        if (attribute != null) {
          attribute.setBaseValue(0.0);
        }
        attribute = cle.getAttribute(Attribute.GENERIC_FOLLOW_RANGE);
        if (attribute != null) {
          attribute.setBaseValue(0.0);
        }
      }
    }
  }

  /**
   * Prevents entities from moving while in a non-clone of a MiniWorld.
   * TODO: Figure out a way to make this work without attributes carrying over to clones of the world.
   * @param e the ChunkLoadEvent.
   */
  @EventHandler
  public void chunkLoad(ChunkLoadEvent e) {
    if (e.getWorld().getName().startsWith("mw/")
          && !e.getWorld().getName().contains("/clone/")) {
      for (Entity entity : e.getChunk().getEntities()) {
        if (entity instanceof LivingEntity) {
          CraftLivingEntity cle = (CraftLivingEntity) entity;
          AttributeInstance attribute = cle.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
          if (attribute != null) {
            attribute.setBaseValue(0.0);
          }
          attribute = cle.getAttribute(Attribute.GENERIC_FLYING_SPEED);
          if (attribute != null) {
            attribute.setBaseValue(0.0);
          }
          attribute = cle.getAttribute(Attribute.GENERIC_FOLLOW_RANGE);
          if (attribute != null) {
            attribute.setBaseValue(0.0);
          }
        }
      }
    }
  }
}
