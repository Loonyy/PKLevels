package com.Sobki.PKLevels;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Sobki.PKLevels.api.PlayerFile;
import com.Sobki.PKLevels.commands.CommandManager;
import com.Sobki.PKLevels.configuration.ConfigManager;
import com.Sobki.PKLevels.listeners.Listeners;
import com.Sobki.PKLevels.paths.PathManager;
import com.Sobki.PKLevels.util.ChatUtils;

import net.milkbowl.vault.permission.Permission;

public class PKLevels extends JavaPlugin {
	
	public PathManager pathManager;
	public Methods methods;
	public ChatUtils chatUtils;
	public PlayerFile playerFile;
	public File classesDirectory;
	public Permission permissions;
	
	@Override
	public void onEnable() {
		
		new ConfigManager(this);
		methods = new Methods(this);
		chatUtils = new ChatUtils(this);
		playerFile = new PlayerFile(this);
		classesDirectory = new File(this.getDataFolder() + File.separator + "paths");
		pathManager = new PathManager(this, methods);
		pathManager.loadAllPaths(classesDirectory);
		new CommandManager(this);
		registerEvents();
		setupPermissions();
		
		for (Player player : getServer().getOnlinePlayers()) {
			playerFile.playerConfigs.put(player, playerFile.config(player));
			playerFile.playerDefaults(player);
			
			methods.loadDataFromFile(player);
		}
	}
	
	@Override
	public void onDisable() {
		for (Player player : getServer().getOnlinePlayers()) {
			methods.saveDataToFile(player);
		}
	}
	
	public void registerEvents() {
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
	}
	
	private boolean setupPermissions() {
		
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		permissions = rsp.getProvider();
		return permissions != null;
	}

}