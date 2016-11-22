package com.Sobki.PKLevels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Methods {
	
	private PKLevels plugin;
	
	private int maxLevel;
	
	public Methods(PKLevels plugin) {
		this.plugin = plugin;
		
		this.maxLevel = plugin.getConfig().getInt("maxlevel");
	}
	
	public void generateExamplePathFile(String name) {
		File file = new File(plugin.classesDirectory.getAbsolutePath(), name + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Integer> exampleAbilities = new HashMap<String, Integer>();
		exampleAbilities.put("WaterManipulation", 1);
		exampleAbilities.put("WaterSpout", 6);
		exampleAbilities.put("Torrent", 13);
		exampleAbilities.put("WaterArms", 18);
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.options().copyDefaults(true);
		config.addDefault("name", name);
		config.addDefault("description", "An example description of what the class is/does.");
		config.addDefault("default", true);
		config.addDefault("element", "Water");
		config.addDefault("expmodifiers.gain", 1.0);
		config.addDefault("expmodifiers.loss", 1.0);
		config.addDefault("maxlevel", 20);
		List<String> abilities = new ArrayList<String>();
		for (String ability : exampleAbilities.keySet()) {
			abilities.add(ability + ":" + exampleAbilities.get(ability));
		}
		config.addDefault("abilities", abilities);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		plugin.pathManager.loadPath(file);
	}
	
	public int getTotalExperience(int level) {
		int[] levels = new int[maxLevel];
		if (level <= levels.length) {
			return levels[levels.length - 1];
		} else if (level < 1) {
			return levels[0];
		}
		
		return levels[level - 1];
	}
	
	public int getExperience(int level) {
		if (level <= 1) {
			return 0;
		}
		
		return getTotalExperience(level) - getTotalExperience(level - 1);
	}
	
	public int getLevel(double exp) {
		int[] levels = new int[maxLevel];
		for (int i = maxLevel; i >= 0; i--) {
			if (exp >= levels[i]) {
				return i + 1;
			}
		}
		
		return -1;
	}

}
