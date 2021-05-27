package com.thekdub.miniworld;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MiniWorldGenerator extends ChunkGenerator {
	/**
	 * The chunk generator for empty MiniWorlds.
	 *
	 * @param world     the world to generate chunks for.
	 * @param random    unused.
	 * @param x         unused.
	 * @param y         unused.
	 * @param biomeGrid unused.
	 * @return the chunk data for the MiniWorld.
	 */
	public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int y, @NotNull
				BiomeGrid biomeGrid) {
		return super.createChunkData(world);
	}
	
}
