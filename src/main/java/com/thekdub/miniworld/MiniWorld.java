package com.thekdub.miniworld;

import com.thekdub.miniworld.listeners.EmptyWorldCleanup;
import com.thekdub.miniworld.listeners.ReturnToOverworld;
import com.thekdub.miniworld.listeners.StopEntityMovement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MiniWorld extends JavaPlugin implements @NotNull Listener {
	
	private static MiniWorld instance;
	
	/**
	 * Returns the current instance of the MiniWorld plugin.
	 *
	 * @return the current instance of the MiniWorld plugin.
	 */
	public static MiniWorld getInstance() {
		return instance;
	}
	
	/**
	 * Loads all worlds within the mw/ directory (if present)
	 * Registers events
	 */
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		MiniWorldLoader.loadWorlds();
		Bukkit.getPluginManager().registerEvents(this, this);
		if (getConfig().getBoolean("halt-mob-movement")) {
			Bukkit.getPluginManager().registerEvents(new StopEntityMovement(), this);
		}
		if (getConfig().getBoolean("automatically-remove-empty-clones")) {
			Bukkit.getPluginManager().registerEvents(new EmptyWorldCleanup(), this);
		}
		Bukkit.getPluginManager().registerEvents(new ReturnToOverworld(), this);
	}
	
	/**
	 * Removes all existing clone worlds from the server.
	 * WARNING: Reloading the server will cause all current clones of worlds to be deleted!
	 */
	public void onDisable() {
		MiniWorldCloner.clean();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Bukkit.getLogger().info(String.format("%s: /%s %s", sender.getName(), command.getLabel(),
					String.join(" ", args)));
		if (command.getLabel().equalsIgnoreCase("miniworld")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.AQUA + "MiniWorld v" + getDescription().getVersion() + " by TheKDub");
				sender.sendMessage(command.getUsage());
				return true;
			}
			if (args[0].equalsIgnoreCase("list")) {
				sender.sendMessage("Worlds List:");
				for (World world : Bukkit.getWorlds()) {
					sender.sendMessage(ChatColor.GRAY + " > " + world.getName());
				}
				return true;
			}
			if (args.length < 2) {
				sender.sendMessage(command.getUsage());
				return true;
			}
			String worldName = (args[1].startsWith("mw/") ? "" : "mw/") + args[1];
			if (args[0].equalsIgnoreCase("create")) {
				World world = MiniWorldAPI.createEmptyWorld(worldName);
				sender.sendMessage("World " + world.getName() + " was created successfully.");
			}
			else if (args[0].equalsIgnoreCase("clone")) {
				World world = Bukkit.getWorld(worldName);
				if (world != null) {
					World world2 = MiniWorldAPI.createCloneOf(world);
					sender.sendMessage("Clone of world " + worldName + " created successfully: " + world2.getName());
				}
				else {
					sender.sendMessage(ChatColor.RED + "World " + worldName + " does not exist!");
				}
			}
			else if (args[0].equalsIgnoreCase("delete")) {
				World world = Bukkit.getWorld(worldName);
				if (world != null) {
					MiniWorldAPI.deleteWorld(world);
					sender.sendMessage("Deleted world " + worldName + " successfully.");
				}
				else {
					sender.sendMessage(ChatColor.RED + "World " + worldName + " does not exist!");
				}
			}
			else if (args[0].equalsIgnoreCase("goto")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Only players may teleport to worlds.");
					return true;
				}
				World world = Bukkit.getWorld(worldName);
				if (world != null) {
					Player player = (Player) sender;
					player.teleport(world.getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
				}
				else {
					sender.sendMessage(ChatColor.RED + "World " + worldName + " does not exist!");
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> response = new ArrayList<>();
		if (args.length == 1) {
			response.add("list");
			response.add("create");
			response.add("clone");
			response.add("delete");
			response.add("goto");
		}
		else if (args.length > 1) {
			if (args[0].equalsIgnoreCase("clone") || args[0].equalsIgnoreCase("delete") ||
						args[0].equalsIgnoreCase("goto")) {
				for (World world : Bukkit.getWorlds()) {
					response.add(world.getName());
				}
			}
		}
		return response;
	}
}
