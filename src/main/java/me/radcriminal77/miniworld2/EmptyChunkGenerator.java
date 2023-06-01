package me.radcriminal77.miniworld2;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * ChunkGenerator that makes an empty world.
 * <p>
 * (taken from <a href="https://www.spigotmc.org/threads/proper-way-of-generating-a-void-world-in-1-18.545239/">this
 * thread</a>)
 */
public class EmptyChunkGenerator extends ChunkGenerator {

    public EmptyChunkGenerator(Location fixedSpawnLocation) {
        this.fixedSpawnLocation = fixedSpawnLocation;
    }

    private final Location fixedSpawnLocation;

    @Override
    public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        return new Location(world, fixedSpawnLocation.getX(), fixedSpawnLocation.getY(), fixedSpawnLocation.getZ());
    }

    @Override
    public boolean shouldGenerateNoise() {
        return false;
    }

    @Override
    public boolean shouldGenerateSurface() {
        return false;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

}
