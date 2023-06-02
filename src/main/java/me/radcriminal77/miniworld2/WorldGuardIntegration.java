package me.radcriminal77.miniworld2;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;

public class WorldGuardIntegration {
    private final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    public void copyRegions(World b_original, World b_target) {
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
