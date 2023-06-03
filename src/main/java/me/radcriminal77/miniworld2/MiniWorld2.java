package me.radcriminal77.miniworld2;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import me.radcriminal77.miniworld2.commands.CreateEmptyWorldCommand;
import me.radcriminal77.miniworld2.commands.CreateMiniatureCommand;
import me.radcriminal77.miniworld2.commands.RemoveMiniatureCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MiniWorld2 extends JavaPlugin implements Listener {

    private static MVWorldManager mvWorldManager;

    @NotNull
    public static MVWorldManager getMvWorldManager() {
        return mvWorldManager;
    }

    private static boolean worldGuardIntegration;

    public static boolean isWorldGuardIntegration() {
        return worldGuardIntegration;
    }

    private static MiniWorld2 instance;

    public static MiniWorld2 getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.getLogger().info("hello world!");

        instance = this;

        this.getServer().getPluginManager().registerEvents(this, this);

        // Get Multiverse Core plugin object, so we can access the MultiVerse plugin API
        MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        assert multiverseCore != null;

        mvWorldManager = multiverseCore.getMVWorldManager();

        this.getCommand("CreateVoidWorld").setExecutor(new CreateEmptyWorldCommand());
        this.getCommand("CreateMiniature").setExecutor(new CreateMiniatureCommand());
        this.getCommand("RemoveMiniature").setExecutor(new RemoveMiniatureCommand());
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent e) {
        // Try to get WorldGuard
        try {
            WorldGuardIntegration.instantiateContainer();
            worldGuardIntegration = true;
            this.getLogger().info("world guard integration on");
        } catch (NoClassDefFoundError ex) {
            // WorldGuard isn't being used
            worldGuardIntegration = false;
            this.getLogger().info("world guard integration off");
        }
    }

    @Override
    public void onDisable() {
        // Remove all miniatures created before MiniWorld2 gets turned off
        MiniWorld2Manager.cleanMiniatures();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        if (id != null && id.equals("empty")) {
            Location spawnLocation = new Location(null, 0, 65, 0);
            return new EmptyChunkGenerator(spawnLocation);
        }

        return null;
    }

}
