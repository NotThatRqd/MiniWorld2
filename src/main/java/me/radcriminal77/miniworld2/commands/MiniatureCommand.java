package me.radcriminal77.miniworld2.commands;

import me.radcriminal77.miniworld2.MiniWorld2Manager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MiniatureCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 2) return false;

        if (args[0].equalsIgnoreCase("create"))
            this.createMiniature(args[1], sender);

        else if (args[0].equalsIgnoreCase("remove"))
            // remove miniature
            this.removeMiniature(args[1], sender);

        else {
            sender.sendPlainMessage("You must either create or remove a miniature!");
            return false;
        }

        return true;
    }

    private static final String  CREATE_MINIATURE_PERM = "MiniWorld2.CreateMiniature";
    private static final String REMOVE_MINIATURE_PERM = "MiniWorld2.RemoveMiniature";

    private void createMiniature(@NotNull String parentWorld, @NotNull CommandSender sender) {
        if (!sender.hasPermission(CREATE_MINIATURE_PERM)) {
            sender.sendPlainMessage("You do not have the required permission (" + CREATE_MINIATURE_PERM + ") to create a miniature.");
            return;
        }

        // Get the world we want to create a miniature of
        World parent = Bukkit.getWorld(parentWorld);

        if (parent == null) {
            sender.sendPlainMessage("could not find that world!");
            return;
        }

        World miniature = MiniWorld2Manager.createMiniatureOf(parent);

        sender.sendPlainMessage("Miniature world " + miniature.getName() + " created.");
    }

    private void removeMiniature(@NotNull String miniatureName, @NotNull CommandSender sender) {
        if (!sender.hasPermission(REMOVE_MINIATURE_PERM)) {
            sender.sendPlainMessage("You do not have the required permission (" + REMOVE_MINIATURE_PERM + ") to remove a miniature.");
            return;
        }

        // Get the world we want to remove
        World miniature = Bukkit.getWorld(miniatureName);

        if (miniature == null) {
            sender.sendPlainMessage("could not find that world!");
            return;
        }

        try {
            MiniWorld2Manager.removeMiniature(miniature);
        } catch (IllegalArgumentException ex) {
            sender.sendPlainMessage("Error: " + ex.getMessage());
        }
    }

}
