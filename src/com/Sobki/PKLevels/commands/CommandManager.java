package com.Sobki.PKLevels.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.commands.admin.AdminCommand;

public class CommandManager {
	
	private String[] aliases = {
		"pklevels", "pklevel", "pkl"	
	};
	
	public CommandManager(final PKLevels plugin) {
		
		PluginCommand command = plugin.getCommand("pklevels");
		new HelpCommand(plugin);
		new StatsCommand(plugin);
		new InfoCommand(plugin);
		new PathsCommand(plugin);
		new SelectCommand(plugin);
		new AbilitiesCommand(plugin);
		new AdminCommand(plugin);
		
		CommandExecutor executor = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[]) {
				if (args.length == 0 && Arrays.asList(aliases).contains(commandLabel.toLowerCase())) {
					for (String line : plugin.getConfig().getStringList("Chat.Messages.GeneralHelp")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
					}
					return true;
				}
				
				List<String> arguments = Arrays.asList(args).subList(1, args.length);
				for (LevelsCommand cmd : LevelsCommand.commands.values()) {
					if (Arrays.asList(cmd.getAliases()).contains(args[0].toLowerCase())) {
						cmd.execute(sender, arguments);
						return true;
					}
				}
				
				for (String line : plugin.getConfig().getStringList("Chat.Messages.GeneralHelp")) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
				}
				return true;
			}
		};
		command.setExecutor(executor);
	}

}
