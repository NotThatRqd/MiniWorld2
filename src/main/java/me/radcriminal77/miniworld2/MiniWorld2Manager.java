package me.radcriminal77.miniworld2;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MiniWorld2Manager {

    private static final String MINIATURE_WORLD_PREFIX = "mw2.miniature";

    /**
     * Helper function that simply makes an empty world with a bedrock platform (using the MultiVerse API)
     * @param name The name of the world (must be a valid Bukkit world name or bad things might happen)
     * @return The world created
     */
    @NotNull
    public static World createEmptyWorld(@NotNull String name) {
        // Create the world using Multiverse API
        MiniWorld2.getMvWorldManager().addWorld(
                name,                     // world name
                World.Environment.NORMAL, // environment type
                null,                     // world seed
                WorldType.NORMAL,         // world type
                false,                    // generate structures
                "MiniWorld2:empty",       // custom generators
                false                     // search for a safe spawn
        );

        // Get the created world
        World world = Bukkit.getWorld(name);
        assert world != null;

        // Put a 32x1x32 pad of bedrock to start out
        for (int x = -16; x < 16; x++) {
            for (int z = -16; z < 16; z++) {
                world.getBlockAt(x, 64, z).setType(Material.BEDROCK);
            }
        }

        return world;
    }

    /**
     * Keeps track of how many clones have been created for a given world.
     * <p>
     * The only problem this may have is that if too many clones for one
     * world are created the integer limit could be reached. Not like that
     * will likely happen though.
     */
    private static final Map<World, Integer> numberOfMiniatures = new HashMap<>();

    /**
     * Creates a "miniature" world <i>(a temporary clone of a world)</i>.
     * Cloned world name will be named using this format:
     * mw2.miniature_[clone id]_[parent world name]</pre>
     *
     * @param world The world to clone
     * @return The clone
     */
    @NotNull
    public static World createMiniatureOf(@NotNull World world) {
        // Get the id for this clone
        int cloneNum = numberOfMiniatures.getOrDefault(world, 0);

        // Increment numberOfClones
        numberOfMiniatures.put(world, cloneNum + 1);

        // Get the name that will be used for this clone
        String cloneName = MINIATURE_WORLD_PREFIX + "_" + cloneNum + "_" + world.getName();

        // Clone the world with Multiverse
        MiniWorld2.getMvWorldManager().cloneWorld(world.getName(), cloneName);

        // Get the clone we just made
        World clonedWorld = Bukkit.getWorld(cloneName);
        assert clonedWorld != null;

        // If world guard integration is on copy the regions from the original world to the clone
        if (MiniWorld2.isWorldGuardIntegration()) {
            WorldGuardIntegration.copyRegions(world, clonedWorld);
        }

        return clonedWorld;
    }

    /**
     * Deletes a miniature world using the Multiverse API
     * @param world The miniature world to delete
     * @throws IllegalArgumentException if the world is not a miniature world
     */
    public static void removeMiniature(@NotNull World world) {
        if (!world.getName().startsWith(MINIATURE_WORLD_PREFIX)) {
            throw new IllegalArgumentException("Provided world was not a miniature world!");
        }

        MiniWorld2.getMvWorldManager().deleteWorld(world.getName());
    }

    /**
     * Loops over every world on the server and deletes it if it is a miniature world
     */
    public static void cleanMiniatures() {
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().startsWith(MINIATURE_WORLD_PREFIX)) {
                removeMiniature(world);
            }
        }
    }

}
