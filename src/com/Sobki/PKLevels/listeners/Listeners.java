package com.Sobki.PKLevels.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.Sobki.PKLevels.PKLevels;

public class Listeners implements Listener {
	
	private PKLevels plugin;
	
	public Listeners(PKLevels plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		plugin.playerFile.playerConfigs.put(player, plugin.playerFile.config(player));
		plugin.playerFile.playerDefaults(player);
		
		plugin.methods.loadDataFromFile(player);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		plugin.methods.saveDataToFile(event.getPlayer());
	}

}
