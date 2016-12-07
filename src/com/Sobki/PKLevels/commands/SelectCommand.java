package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.api.PlayerData;
import com.Sobki.PKLevels.paths.PlayerPath;

public class SelectCommand extends LevelsCommand {
	
	private PKLevels plugin;

	public SelectCommand(PKLevels plugin) {
		super(plugin, "select", "/pkl select <path>", "Select your path", new String[] { "select", "choose", "se", "ch" });
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 1, 1) || !isPlayer(sender)) {
			return;
		}
		Player player = (Player) sender;
		PlayerPath targetPath = plugin.pathManager.getPath(args.get(0));
		PlayerData data = PlayerData.getPlayerData(player);
		if (targetPath == null) {
			player.sendMessage(plugin.chatUtils.getErrorPrefix() + " " + plugin.chatUtils.getErrorInvalidPath());
			return;
		}
		if (data == null) {
			return;
		}
		
		data.setPath(targetPath);
		player.sendMessage(plugin.chatUtils.getChatPrefix() + " " + plugin.chatUtils.getPathJoinedMsg().replace("{PATH}", targetPath.getName()));
		
	}
	
}
