package me.radcriminal77.miniworld2;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import me.radcriminal77.miniworld2.commands.CreateEmptyWorldCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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

        this.getCommand("CreateEmptyWorld").setExecutor(new CreateEmptyWorldCommand());
    }

}
