package com.Sobki.PKLevels.configuration;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

	public static ConfigClass defaultConfig;
	public static ConfigClass languageConfig;

	public ConfigManager(Plugin plugin) {

		defaultConfig = new ConfigClass("config.yml");
		languageConfig = new ConfigClass("Language.yml");

		configCheck(ConfigType.DEFAULT);
		configCheck(ConfigType.LANGUAGE);
	}

	public static void configCheck(ConfigType type) {
		FileConfiguration config;

		// Default Config
		if (type == ConfigType.DEFAULT) {
			config = defaultConfig.get();

			config.options().copyDefaults(true);

			config.addDefault("Storage.MySQL.host", "localhost");
			config.addDefault("Storage.MySQL.port", 3306);
			config.addDefault("Storage.MySQL.pass", "");
			config.addDefault("Storage.MySQL.db", "minecraft");
			config.addDefault("Storage.MySQL.user", "root");

			config.addDefault("maxlevel", Integer.valueOf(100));
			config.addDefault("levelscale", Double.valueOf(1.2));
			config.addDefault("baseexp", Integer.valueOf(110));

			defaultConfig.saveConfig();

		}

		// Language Config
		if (type == ConfigType.LANGUAGE) {
			config = languageConfig.get();
			config.options().copyDefaults(true);

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

			languageConfig.saveConfig();
		}

	}

	public static FileConfiguration getConfig() {

		return ConfigManager.defaultConfig.get();
	}

	public static FileConfiguration getLanguageConfig() {

		return ConfigManager.languageConfig.get();
	}

	public static void saveConfig() {
		defaultConfig.saveConfig();
		languageConfig.saveConfig();

	}

}
