package com.Sobki.PKLevels.configuration;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	
	public ConfigManager(Plugin plugin) {
		
		FileConfiguration config = plugin.getConfig();
		config.options().copyDefaults(true);

		config.addDefault("Storage.MySQL.host", "localhost");
		config.addDefault("Storage.MySQL.port", 3306);
		config.addDefault("Storage.MySQL.pass", "");
		config.addDefault("Storage.MySQL.db", "minecraft");
		config.addDefault("Storage.MySQL.user", "root");

		config.addDefault("Chat.Prefix.Default", "&5>>>");
		config.addDefault("Chat.Prefix.Error", "&4[&cERROR&4]");
		
		List<String> generalHelp = new ArrayList<String>();
		generalHelp.add("&fType &a/pkl help &ffor help.");
		
		config.addDefault("Chat.Messages.PathJoined", "&fYou have joined the path of the &5{PATH}&f!");
		config.addDefault("Chat.Messages.GeneralHelp", generalHelp);

		config.addDefault("Chat.Messages.Errors.InvalidArguments", "&cInvalid arguments...");
		config.addDefault("Chat.Messages.Errors.InsufficientPermission", "&cInsufficient permission...");
		config.addDefault("Chat.Messages.Errors.NotEnoughArgs", "&cNot enough arguments...");
		config.addDefault("Chat.Messages.Errors.MustBePlayer", "&cYou must be a player to use this command...");
		config.addDefault("Chat.Messages.Errors.InvalidPath", "&cInvalid path name...");

		config.addDefault("maxlevel", Integer.valueOf(100));
		config.addDefault("levelscale", Double.valueOf(1.2));
		config.addDefault("baseexp", Integer.valueOf(110));
		
		plugin.saveConfig();
	}

}
