package me.radcriminal77.miniworld2;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class WorldGuardIntegration {
    private static RegionContainer container = null;
    
    public static void instantiateContainer() {
        // We assert container == null because this function should only ever get run once
        assert container == null;
        container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    public static void copyRegions(@NotNull World b_original, @NotNull World b_target) {
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
