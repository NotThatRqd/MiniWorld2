package me.radcriminal77.miniworld2;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import me.radcriminal77.miniworld2.commands.CreateEmptyWorldCommand;
import me.radcriminal77.miniworld2.commands.CreateMiniatureCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class MiniWorld2 extends JavaPlugin {

    private static MVWorldManager mvWorldManager;

    public static MVWorldManager getMvWorldManager() {
        return mvWorldManager;
    }

    @Override
    public void onEnable() {
        this.getLogger().info("hello world!");

        // Get Multiverse Core plugin object, so we can access the MultiVerse plugin API
        MultiverseCore multiverseCore = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        assert multiverseCore != null;

        mvWorldManager = multiverseCore.getMVWorldManager();

        this.getCommand("CreateVoidWorld").setExecutor(new CreateEmptyWorldCommand());
        this.getCommand("CreateMiniature").setExecutor(new CreateMiniatureCommand());
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
