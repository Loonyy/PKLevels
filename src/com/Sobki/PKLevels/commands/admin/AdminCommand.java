package com.Sobki.PKLevels.commands.admin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.commands.LevelsCommand;

public class AdminCommand extends LevelsCommand {

	private String[] expaliases = { "experience", "exp", "xp" };
	private String[] levelaliases = { "level", "lvl" };
	private String[] selectaliases = { "select", "s" };

	public AdminCommand(PKLevels plugin) {
		super(plugin, "exp", "/pkl admin <args>", "All admin related commands.",
				new String[] { "admin" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 4)) {
			return;
		} else if (args.size() <= 2) {
			
			Bukkit.broadcastMessage("help command");
			
		}else if (args.size() == 3) {
			
			if (Arrays.asList(selectaliases).contains(args.get(0))) {
				Bukkit.broadcastMessage("select command");
			}
			
		} else if (args.size() == 4) {
			
			if (Arrays.asList(expaliases).contains(args.get(0))) {
				
				Bukkit.broadcastMessage("exp command");
				
			} else if (Arrays.asList(levelaliases).contains(args.get(0))) {
				
				Bukkit.broadcastMessage("level command");
				
			}
		}

	}

	/*
		/pkl admin exp/xp <player> <path> <exp>
		/pkl admin level/lvl <player> <path> <level>
		/pkl admin select <player> <path>
	 */

}
