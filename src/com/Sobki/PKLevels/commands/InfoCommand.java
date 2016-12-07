package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.paths.PlayerPath;

import net.md_5.bungee.api.ChatColor;

public class InfoCommand extends LevelsCommand {

	private PKLevels plugin;

	public InfoCommand(PKLevels plugin) {
		super(plugin, "info", "/pkl info <path>", "Displays info. regarding a path.", new String[] { "info", "i" });
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 1, 1)) {
			return;
		}

		PlayerPath path = plugin.pathManager.getPath(args.get(0));
		if (path == null) {
			sender.sendMessage(plugin.chatUtils.getErrorPrefix() + " " + plugin.chatUtils.getErrorInvalidPath());
			return;
		}

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Information &d- &f" + path.getName() + "&5:"));
		sender.sendMessage("");
		sender.sendMessage(ChatColor.WHITE + path.getDescription());
	}

}
