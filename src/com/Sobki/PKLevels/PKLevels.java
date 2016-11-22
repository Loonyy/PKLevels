package com.Sobki.PKLevels;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.Sobki.PKLevels.api.PlayerData;
import com.Sobki.PKLevels.configuration.ConfigManager;
import com.Sobki.PKLevels.paths.PathManager;

import net.milkbowl.vault.permission.Permission;

public class PKLevels extends JavaPlugin {
	
	public PathManager pathManager;
	public Methods methods;
	public File classesDirectory;
	public Permission permissions;
	
	@Override
	public void onEnable() {
		
		new ConfigManager(this);
		methods = new Methods(this);
		classesDirectory = new File(this.getDataFolder() + File.separator + "paths");
		pathManager = new PathManager(this, methods);
		pathManager.loadAllPaths(classesDirectory);
		registerCommands();
		setupPermissions();
		
		for (Player player : getServer().getOnlinePlayers()) {
			PlayerData playerData = new PlayerData(this, player);
			playerData.addExp(playerData.getPath(), 500);
			playerData.setPath(pathManager.getPath("Waterbender"));
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public void onLoad() {
		
	}
	
	private void registerCommands() {
		
	}
	
	private boolean setupPermissions() {
		
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		permissions = rsp.getProvider();
		return permissions != null;
	}

}