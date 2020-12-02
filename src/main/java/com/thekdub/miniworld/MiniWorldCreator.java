package com.thekdub.miniworld;

import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;

public class MiniWorldCreator extends WorldCreator {
  /**
   * WorldCreator for empty MiniWorlds.
   * @param name the desired name for the world.
   */
  public MiniWorldCreator(String name) {
    super((name.startsWith("mw/") ? "" : "mw/") + name);
    this.generateStructures(false);
    this.generator(new MiniWorldGenerator());
  }
}
