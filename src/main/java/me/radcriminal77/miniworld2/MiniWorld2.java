package me.radcriminal77.miniworld2;

import me.radcriminal77.miniworld2.commands.CreateEmptyWorldCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniWorld2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLogger().info("hello world!");

        this.getCommand("CreateEmptyWorld").setExecutor(new CreateEmptyWorldCommand());
    }

}
