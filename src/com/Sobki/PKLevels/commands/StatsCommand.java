package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.api.PlayerData;

public class StatsCommand extends LevelsCommand {

	private PKLevels plugin;

	public StatsCommand(PKLevels plugin) {
		super(plugin, "stats", "/pkl stats [player]", "Displays statistics on the targeted player.",
				new String[] { "stats", "st" });
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 1)) {
			return;
		} else if (args.size() == 0) {
			if (isPlayer(sender)) {
				displayStats((Player) sender);
			}
		} else if (args.size() == 1) {
			Player target = (Player) plugin.getServer().getPlayer(args.get(0));
			if (target == null) {
				return;
			}
			displayStats(sender, target);
		}

	}

	public void displayStats(Player player) {
		displayStats(player, player);
	}

	public void displayStats(CommandSender sender, Player target) {
		PlayerData data = PlayerData.getPlayerData(target);
		if (data == null) {
			return;
		}
		double currentXp = (data.getExperience(data.getPath()) - plugin.methods.getExperience(data.getLevel(data.getPath())));
		double requiredXp = (plugin.methods.getExperience(data.getLevel(data.getPath()) + 1));
		int percentage = (int) ((currentXp / requiredXp) * 100 * 0.5);
		String s1 = new String(new char[percentage]).replace("\0", "|");
		String s2 = new String(new char[50 - percentage]).replace("\0", "|");
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d-&5< &f" + target.getName() + "'s Statistics &5>&d-"));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&5Path: &f" + data.getPath()));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Level: &f" + data.getLevel(data.getPath())));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Experience: &f" + (int) currentXp + "&5/&f" + (int) requiredXp));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[ &d" + s1 + "&8" + s2 + " &f&l]"));
		sender.sendMessage("");
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5Unlocked Abilities:"));
		for (String ability : data.getUnlockedAbilities()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5- &f" + ability));
		}
	}

}