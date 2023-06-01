package me.radcriminal77.miniworld2;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MiniWorld2Manager {

    /**
     * Appends "mw2." to a string if it does not already start with it
     * @param original The string to convert
     * @return The converted string
     */
    @Contract(pure = true)
    public static String convertToMiniWorldName(@NotNull String original) {
        if (original.startsWith("mw2.")) {
            return original;
        }

        return "mw2." + original;
    }

    @NotNull
    public static World createEmptyWorld(@NotNull String name) {
        // Get the final name that is going to be used
        String worldName = convertToMiniWorldName(name);

        // Make our EmptyChunkGenerator
        Location spawnLocation = new Location(null, 0, 64, 0);
        EmptyChunkGenerator generator = new EmptyChunkGenerator(spawnLocation);

        // Make our empty world
        World world = new WorldCreator(worldName)
                .generateStructures(false)
                .generator(generator)
                .createWorld();
        assert world != null;

        // Put a 32x1x32 pad of bedrock to start out
        for (int x = -16; x < 16; x++) {

            for (int z = -16; z < 16; z++) {

                world.getBlockAt(x, 63, z).setType(Material.BEDROCK);

            }

        }

        return world;
    }

}
