package com.Sobki.PKLevels.commands;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Sobki.PKLevels.PKLevels;

public class HelpCommand extends LevelsCommand {

	public HelpCommand(PKLevels plugin) {
		super(plugin, "help", "/pkl help", "Display help information", new String[] { "help", "h" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 1)) {
			return;
		} else if (args.size() == 0) {
			List<String> msg = new ArrayList<>();
			for (LevelsCommand command : commands.values()) {
				if (!command.getName().equalsIgnoreCase("help") && command.hasPermission(sender)) {
					msg.add(ChatColor.translateAlternateColorCodes('&', "&d" + command.getUsage() + " &5- &f" + command.getDescription()));
				}
			}
			Collections.sort(msg);
			Collections.reverse(msg);
			msg.add(ChatColor.translateAlternateColorCodes('&', "&d" + commands.get("help").getUsage() + " &5- &f" + commands.get("help").getDescription()));
			Collections.reverse(msg);
			for (String s : getPage(msg, 1, false)) {
				sender.sendMessage(s);
			}
			return;
		}
		
		String arg = args.get(0).toLowerCase();
		if (isNumeric(arg)) {
			List<String> strings = new ArrayList<String>();
			for (LevelsCommand command : commands.values()) {
				strings.add(ChatColor.translateAlternateColorCodes('&', "&d" + command.getUsage() + " &5- &f" + command.getDescription()));
			}
			for (String s : getPage(strings, Integer.valueOf(arg), true)) {
				sender.sendMessage(s);
			}
		}
		
	}
	
	protected boolean isNumeric(String id) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(id, pos);
		return id.length() == pos.getIndex();
	}

}
