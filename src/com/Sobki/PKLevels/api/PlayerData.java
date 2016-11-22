package com.Sobki.PKLevels.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.Sobki.PKLevels.PKLevels;
import com.Sobki.PKLevels.paths.PlayerPath;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.Ability;
import com.projectkorra.projectkorra.ability.CoreAbility;

public class PlayerData {
	
	public static HashMap<UUID, PlayerData> players = new HashMap<>();
	
	private PKLevels plugin;
	private Player player;
	private BendingPlayer bPlayer;
	private PlayerPath path;
	private Map<PlayerPath, Double> experience = new HashMap<>();
	
	public PlayerData(PKLevels plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		this.bPlayer = BendingPlayer.getBendingPlayer(player);
		
		players.put(player.getUniqueId(), this);
	}
	
	public void addAbility(String ability) {
		plugin.permissions.playerRemoveTransient(player, "-bending.ability." + ability);
	}
	
	public void removeAbility(String ability) {
		plugin.permissions.playerAddTransient(player, "-bending.ability." + ability);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public BendingPlayer getBendingPlayer() {
		return this.bPlayer;
	}
	
	public PlayerPath getPath() {
		return this.path;
	}
	
	public void setPath(PlayerPath path) {
		this.path = path;
		negateAll();
		setupAbilities();
	}
	
	public List<String> getUnlockedAbilities() {
		List<String> abilities = new ArrayList<>();
		for (String ability : path.getAbilities()) {
			if (path.getAbilityLevels().get(ability) <= experience.get(path)) {
				abilities.add(ability);
			}
		}
		
		return abilities;
	}
	
	public void negateAll() {
		for (Ability ability : CoreAbility.getAbilities()) {
			removeAbility(ability.getName());
		}
	}
	
	public void setupAbilities() {
		for (String ability : getUnlockedAbilities()) {
			addAbility(ability);
		}
	}
	
	public void addExp(PlayerPath path, double experience) {
		double exp = getExperience(path) + experience;
		exp = exp < 0 ? 0 : exp;
		int currentLevel = getLevel(path);
		setExperience(path, exp);
		
		// Possible add experience change event
		
		int newLevel = plugin.methods.getLevel(exp);
		if (currentLevel != newLevel) {
			
			// Level change event
			
			if (newLevel >= path.getMaxLevel()) {
				setExperience(path, plugin.methods.getTotalExperience(path.getMaxLevel()));
				Bukkit.broadcastMessage(player.getName() + " is now a master " + path.getName() + "!");
			}
			if (newLevel > currentLevel) {
				player.sendMessage("You have gained a level! ( Lvl " + newLevel + " " + path.getName() + " )");
			} else {
				player.sendMessage("You have lost a level. ( Lvl " + newLevel + " " + path.getName() + " )");
			}
			
		}
	}
	
	public double getExperience(PlayerPath path) {
		return this.experience.containsKey(path) ? this.experience.get(path) : -1;
	}
	
	public void setExperience(PlayerPath path, double experience) {
		this.experience.put(path, experience);
	}
	
	public int getLevel(PlayerPath path) {
		return this.experience.containsKey(path) ? plugin.methods.getLevel(this.experience.get(path)) : -1;
	}
	
	public double expRequiredToLevel(PlayerPath path) {
		return this.experience.containsKey(path) ? getExperience(path) - plugin.methods.getTotalExperience(getLevel(path)) : -1;
	}
	
	public boolean isMaster(PlayerPath path) {
		return getLevel(path) >= path.getMaxLevel();
	}
	
	/*
	 * ExpRequiredToLevelUp * How much experience they need to level up - MAYBE PUT IN BLMETHODS
	 * 
	 * CalculateExpLoss * Experience lost on death
	 * 
	 */
	
	public static PlayerData getPlayerData(UUID uuid) {
		return players.containsKey(uuid) ? players.get(uuid) : null;
	}
	
	public static PlayerData getBender(Player player) {
		return getPlayerData(player.getUniqueId());
	}
	
	public static PlayerData getBender(String name) {
		return getPlayerData(Bukkit.getServer().getPlayer(name).getUniqueId());
	}

}
