package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.api.PlayerData;

public class AbilitiesCommand extends LevelsCommand {

	private PKLevels plugin;

	public AbilitiesCommand(PKLevels plugin) {
		super(plugin, "abilities", "/pkl abilities", "Lists all unlocked abilities", new String[] { "abilities", "a" });
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 0) || !isPlayer(sender)) {
			return;
		}
		Player player = (Player) sender;
		PlayerData data = PlayerData.getPlayerData(player);
		if (data == null) {
			return;
		}
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dUnlocked Abilities:"));
		sender.sendMessage("");
		for (String ability : data.getUnlockedAbilities()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5- &f" + ability));
		}
		sender.sendMessage("");
		sender.sendMessage(plugin.chatUtils.getChatPrefix()
				+ ChatColor.translateAlternateColorCodes('&', " &fTo bind an ability, use &d/b b <ability> [#slot] &ffor more information."));
		sender.sendMessage(plugin.chatUtils.getChatPrefix()
				+ ChatColor.translateAlternateColorCodes('&', " &fFor help on how to use an ability, use &d/b b <ability>&f."));

	}

}
