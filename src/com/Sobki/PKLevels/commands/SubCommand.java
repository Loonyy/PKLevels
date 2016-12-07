package com.Sobki.PKLevels.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface SubCommand {
	
	public String getName();
	
	public String[] getAliases();
	
	public String getUsage();
	
	public String getDescription();
	
	public void help(CommandSender sender, boolean description);
	
	public void execute(CommandSender sender, List<String> args);

}
