package me.radcriminal77.miniworld2.commands;

import me.radcriminal77.miniworld2.MiniWorld2Manager;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CreateEmptyWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) return false;

        World newWorld = MiniWorld2Manager.createEmptyWorld(args[0]);
        sender.sendPlainMessage("World " + newWorld.getName() + " created.");

        return true;
    }

}
