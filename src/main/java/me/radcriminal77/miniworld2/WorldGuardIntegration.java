package me.radcriminal77.miniworld2;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * A package-private class for interacting with the WorldGuard api
 */
final class WorldGuardIntegration {
    private static RegionContainer container = null;

    /**
     * Before this class is ready for use this method must be called.
     * It's kind of like a constructor, but static. It is also used
     * to check for WorldGuard support in the main plugin class.
     */
    static void instantiateContainer() {
        // We assert container == null because this function should only ever get run once
        assert container == null;
        container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    /**
     * Copies the regions (and flags of those regions) from one world to another
     * @param b_original The (Bukkit) world to copy from
     * @param b_target The (Bukkit) world to copy to
     */
    static void copyRegions(@NotNull World b_original, @NotNull World b_target) {
        com.sk89q.worldedit.world.World original = BukkitAdapter.adapt(b_original);
        com.sk89q.worldedit.world.World target = BukkitAdapter.adapt(b_target);

        RegionManager originalManager = container.get(original);
        RegionManager targetManager = container.get(target);

        if (originalManager == null || targetManager == null) {
            System.out.println("error getting region data");
            return;
        }

        // Set target world's regions to original world's regions
        targetManager.setRegions(originalManager.getRegions());

    }
}
