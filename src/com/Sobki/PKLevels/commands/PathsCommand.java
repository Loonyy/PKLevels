package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.paths.PlayerPath;

public class PathsCommand extends LevelsCommand {

	private PKLevels plugin;

	public PathsCommand(PKLevels plugin) {
		super(plugin, "paths", "/pkl paths", "Lists all available paths", new String[] { "paths", "p" });
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 0)) {
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dAvailable Paths:"));
		sender.sendMessage("");
		for (PlayerPath path : plugin.pathManager.getPaths()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5- &f" + path.getName()));
		}
		sender.sendMessage(plugin.chatUtils.getChatPrefix()
				+ ChatColor.translateAlternateColorCodes('&', " &fType &d/pkl info <path> &ffor more information."));
		sender.sendMessage(plugin.chatUtils.getChatPrefix()
				+ ChatColor.translateAlternateColorCodes('&', " &fType &d/pkl select <path> &fto select a path."));

	}

}
