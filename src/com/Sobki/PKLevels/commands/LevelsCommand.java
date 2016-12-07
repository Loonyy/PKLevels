package com.Sobki.PKLevels.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;

public abstract class LevelsCommand implements SubCommand {
	
	private PKLevels plugin;
	private final String name;
	private final String usage;
	private final String description;
	private final String[] aliases;
	
	public static Map<String, LevelsCommand> commands = new HashMap<>();
	
	public LevelsCommand(PKLevels plugin, String name, String usage, String description, String[] aliases) {
		this.plugin = plugin;
		this.name = name;
		this.usage = usage;
		this.description = description;
		this.aliases = aliases;
		
		commands.put(name, this);
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String[] getAliases() {
		return aliases;
	}
	
	public void help(CommandSender sender, boolean desc) {
		sender.sendMessage(ChatColor.GRAY + "Usage: " + usage);
		if (desc) {
			sender.sendMessage(ChatColor.GRAY + description);
		}
	}
	
	protected boolean hasPermission(CommandSender sender) {
		if (sender.hasPermission("pklevels.command." + name)) {
			return true;
		} else {
			sender.sendMessage(plugin.chatUtils.getErrorPrefix() + " " + plugin.chatUtils.getErrorInsufficientPermission());
			return false;
		}
	}
	
	protected boolean correctLength(CommandSender sender, int size, int min, int max) {
		if (size < min || size > max) {
			help(sender, false);
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean isPlayer(CommandSender sender) {
		if (sender instanceof Player) {
			return true;
		} else {
			sender.sendMessage(plugin.chatUtils.getErrorPrefix() + " " + plugin.chatUtils.getErrorMustBePlayer());
			return false;
		}
	}
	
	protected List<String> getPage(List<String> entries, int page, boolean sort) {
		List<String> strings = new ArrayList<String>();
		if (sort) {
			Collections.sort(entries);
		}
		
		if (page < 1) {
			page = 1;
		}
		if ((page * 8) - 8 >= entries.size()) {
			page = Math.round(entries.size() / 8) + 1;
			if (page < 1) {
				page = 1;
			}
		}
		strings.add(ChatColor.DARK_PURPLE + "PKLevels Help " + ChatColor.DARK_GRAY + "- [" + ChatColor.LIGHT_PURPLE + page + "/" + (int) Math.ceil((entries.size()+.0)/(8+.0)) + ChatColor.DARK_GRAY + "]");
		strings.add("");
		if (entries.size() > ((page * 8) - 8)) {
			for (int i = ((page * 8) - 8); i < entries.size(); i++) {
				if (entries.get(i) != null) {
					strings.add(entries.get(i).toString());
				}
				if (i >= (page * 8)-1) {
					break;
				}
			}
		}
		return strings;
	}

}
