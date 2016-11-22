package com.Sobki.PKLevels.paths;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.Sobki.PKLevels.Methods;
import com.projectkorra.projectkorra.Element;

public class PathManager {
	
	private Plugin plugin;
	private Methods methods;
	private Set<PlayerPath> paths;
	private PlayerPath defaultPath;
	
	public PathManager(Plugin plugin, Methods methods) {
		this.plugin = plugin;
		this.methods = methods;
		this.paths = new HashSet<>();
	}
	
	/**
	 * Adds specified path into the path manager
	 * 
	 * @param path
	 * @return true if the path was successfully added
	 */
	public boolean addPath(PlayerPath path) {
		return paths.add(path);
	}
	
	/**
	 * Removes specified path from the path manager
	 * 
	 * @param path
	 * @return true if the path was successfully removed
	 */
	public boolean removePath(PlayerPath path) {
		return paths.remove(path);
	}
	
	/**
	 * Get a path via name. If invalid name, return null
	 * 
	 * @param path
	 * @return the path's class
	 */
	public PlayerPath getPath(String path) {
		for (PlayerPath p : paths) {
			if (p.getName().equalsIgnoreCase(path)) {
				return p;
			}
		}
		
		return null;
	}
	
	/**
	 * @return all paths
	 */
	public Set<PlayerPath> getPaths() {
		return this.paths;
	}
	
	/**
	 * @return the default path new players will be put into
	 */
	public PlayerPath getDefaultPath() {
		return this.defaultPath;
	}
	
	/**
	 * Loads all paths from a given directory into path manager
	 * 
	 * @param file
	 */
	public void loadAllPaths(File file) {
		file.mkdirs();
		if (file.listFiles().length == 0) {
			plugin.getLogger().warning("Your paths directory is empty. No paths were loaded.");
			plugin.getLogger().info("Generating a default path.");
			methods.generateExamplePathFile("ExamplePath");
		}
		for (File f : file.listFiles()) {
			if (f.isFile() && f.getName().contains(".yml")) {
				loadPath(f);
			}
		}
		
		if (defaultPath == null) {
			plugin.getLogger().severe("You are missing a default path! Disabling BendingLevels.");
			Bukkit.getServer().getPluginManager().disablePlugin(plugin);
			return;
		}
	}
	
	/**
	 * Loads the PlayerPath from a specified file
	 * 
	 * @param file
	 * @return the PlayerPath, or null in the instance of an error
	 */
	public void loadPath(File file) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		String pathName = config.getString("name");
		if (pathName == null) {
			plugin.getLogger().warning("Error when loading path from file '" + file.getName() + "'! Make sure this is a valid path file.");
			return;
		}
		
		PlayerPath path = new PlayerPath(pathName);
		int definitiveMaxLevel = plugin.getConfig().getInt("maxlevel");
		int maxLevel = config.getInt("maxlevel", definitiveMaxLevel);
		if (maxLevel < 1) {
			plugin.getLogger().warning("Path '" + path.getName() + "' has a max level of " + maxLevel + ", which recedes the minimum, max level value. Setting the max level to 1.");
			maxLevel = 1;
		} else if (maxLevel > definitiveMaxLevel) {
			plugin.getLogger().warning("Path '" + path.getName() + "' has a max level of " + maxLevel + ", which exceeds the maximum, max level value. Setting the max level to " + definitiveMaxLevel + ".");
			maxLevel = definitiveMaxLevel;
		}
		List<String> abilities = config.getStringList("abilities");
		Map<String, Integer> abilityLevels = new HashMap<>();for (String ability : abilities) {
			String[] abil = ability.split(":");
			if (abil[0] != null && abil[1] != null) {
				try {
					int level = Integer.parseInt(abil[1]);
					abilityLevels.put(abil[0], level);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		path.setDescription(config.getString("description", ""));
		path.setElement(Element.getElement(config.getString("element")));
		path.setExpGainModifier(config.getDouble("expmodifiers.gain", 1.0));
		path.setExpLossModifier(config.getDouble("expmodifiers.loss", 1.0));
		path.setMaxLevel(maxLevel);
		path.setAbilityLevels(abilityLevels);
		
		
		
		if (config.getBoolean("default", false)) {
			plugin.getLogger().info("'" + path.getName() + "' has been determined as the default path.");
			defaultPath = path;
		}
		
		if (!addPath(path)) {
			plugin.getLogger().warning("Duplicate path found (" + path.getName() + ").");
		} else {
			plugin.getLogger().info("Successfully loaded " + path.getName() + "!");
		}
	}
	
	/**
	 * Set the default path given to new players
	 * 
	 * @param path
	 */
	public void setDefaultPath(PlayerPath path) {
		this.defaultPath = path;
	}

}
