package com.thekdub.miniworld;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
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
	public @Nonnull ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int y, @Nonnull
				BiomeGrid biomeGrid) {
		return super.createChunkData(world);
	}
	
}
