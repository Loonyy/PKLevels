package com.Sobki.PKLevels.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	
	public ConfigManager(Plugin plugin) {
		
		FileConfiguration config = plugin.getConfig();
		
		config.addDefault("maxlevel", Integer.valueOf(100));
		
		plugin.saveConfig();
	}

}
