package com.Sobki.PKLevels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.api.PlayerData;
import com.Sobki.PKLevels.paths.PlayerPath;

public class Methods {

	private PKLevels plugin;

	private int maxLevel;
	private double scale;
	private int base;

	public Methods(PKLevels plugin) {
		this.plugin = plugin;

		this.maxLevel = plugin.getConfig().getInt("maxlevel");
		this.scale = plugin.getConfig().getDouble("levelscale");
		this.base = plugin.getConfig().getInt("baseexp");
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
		config.addDefault("description", "An example description of what the path is/does.");
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
	}

	public void saveDataToFile(Player player) {

		PlayerData data = PlayerData.getPlayerData(player);
		if (data == null) {
			return;
		}
		plugin.playerFile.set(player, "name", player.getName());
		plugin.playerFile.set(player, "current_path", data.getPath().getName());
		for (PlayerPath path : plugin.pathManager.getPaths()) {
			plugin.playerFile.set(player, "experience." + path.getName(), data.getExperience(path));
		}
	}

	public void loadDataFromFile(Player player) {

		PlayerData data = new PlayerData(plugin, player);
		PlayerPath path = plugin.pathManager.getPath(plugin.playerFile.getString(player, "current_path"));
		if (path == null) {
			data.setPath(plugin.pathManager.getDefaultPath());
		} else {
			data.setPath(path);
		}
		for (PlayerPath p : plugin.pathManager.getPaths()) {
			double exp = plugin.playerFile.getDouble(player, "experience." + p.getName());
			if (exp == -1) {
				exp = 0.0;
			}
			Bukkit.broadcastMessage(p.getName() + ": " + exp);
			data.setExperience(p, exp);
		}

	}
	
	/*
	 * Experience level calculations by the following spreadsheet.
	 * https://docs.google.com/spreadsheets/d/1a3MhVkqA1jyeShES4pUzv-8sw30Y5ciMmelONdpo6ns/edit#gid=0
	 */

	public int getExperience(int level) {
		if (level > maxLevel) {
			return -1;
		}
		return (int) Math.round((Math.pow(level * scale, 1.5)) * base);
	}

	public int getLevel(double exp) {
		int lvl = (int) Math.nextDown(Math.pow(exp / 110, 1.0/1.5) / 1.2);
		if (lvl > maxLevel) {
			return -1;
		}
		return lvl;
	}

}