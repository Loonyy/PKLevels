package com.Sobki.PKLevels.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;

public class PlayerFile {

	private PKLevels plugin;

	public PlayerFile(PKLevels plugin) {
		this.plugin = plugin;
	}

	public File getPlayerF(Player p) {
		File file = new File(plugin.getDataFolder() + "/player_data/" + p.getUniqueId().toString() + ".yml");

		return file;
	}

	public FileConfiguration config(Player p) {

		return YamlConfiguration.loadConfiguration(getPlayerF(p));
	}

	public void save(Player p) throws IOException {

		playerConfigs.get(p).save(getPlayerF(p));
	}

	public HashMap<Player, FileConfiguration> playerConfigs = new HashMap<Player, FileConfiguration>();

	public void check(Player p, String key, Object value) throws IOException {

		if (!playerConfigs.get(p).isSet(key)) {

			playerConfigs.get(p).set(key, value);
			try {
				save(p);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return;
		}

	}

	public boolean getBoolean(Player p, String key) {

		return playerConfigs.get(p).getBoolean(key);
	}

	public int getInt(Player p, String key) {

		return playerConfigs.get(p).getInt(key);
	}
	
	public double getDouble(Player p, String key) {
		
		return playerConfigs.get(p).getDouble(key);
	}

	public Object get(Player p, String str) {

		return playerConfigs.get(p).get(str);
	}

	public String getString(Player p, String key) {

		return playerConfigs.get(p).getString(key);
	}

	public List<?> getList(Player p, String key) {

		return playerConfigs.get(p).getList(key);
	}

	public List<String> getStringList(Player p, String key) {

		return playerConfigs.get(p).getStringList(key);
	}

	public ConfigurationSection getSection(Player p, String key) {

		return playerConfigs.get(p).getConfigurationSection(key);
	}

	public void set(Player p, String key, Object value) {

		playerConfigs.get(p).set(key, value);

		try {
			save(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void playerDefaults(Player player) {
		try {

			check(player, "name", player.getName());
			
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
}