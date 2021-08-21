package com.thekdub.miniworld;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.util.Objects;

public class MiniWorldLoader {
	
	/**
	 * Unloads all MiniWorlds from the server.
	 */
	protected static void unloadWorlds() {
		for (World world : Bukkit.getWorlds()) {
			if (world.getName().startsWith("mw/")) {
				Bukkit.unloadWorld(world, true);
			}
		}
	}
	
	/**
	 * Loads all MiniWorlds into the server.
	 */
	protected static void loadWorlds() {
		File mw = new File("mw");
		if (!mw.exists()) {
			return;
		}
		for (File file : Objects.requireNonNull(mw.listFiles())) {
			World world = Bukkit.createWorld(new MiniWorldCreator(file.getPath().replace("\\", "/")));
		}
	}
	
}
