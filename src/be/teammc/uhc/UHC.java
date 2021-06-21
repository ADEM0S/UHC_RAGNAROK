package be.teammc.uhc;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import be.teammc.uhc.commands.RoleCmd;
import be.teammc.uhc.commands.SpawnCmd;
import be.teammc.uhc.commands.UHCCmd;
import be.teammc.uhc.listeners.ChatListener;
import be.teammc.uhc.listeners.InventoryListener;
import be.teammc.uhc.listeners.JoinQuitListener;
//import be.teammc.uhc.listeners.ScoreboardEvents;
import be.teammc.uhc.managers.ConfigManager;
import be.teammc.uhc.managers.ProfileManager;

public class UHC extends JavaPlugin {
	
	private static Logger logger;
	
	private ConfigManager configManager;
	private ProfileManager profileManager;
	
	public void onEnable() 
	{
		logger = getLogger();
		
		System.out.println(" ");
		System.out.println("\u001B[32m  Plugin UHC mis en route.\u001B[0m");
		System.out.println(" ");
		
		// BIOME DE MIDDLE = DARK FOREST POUR ESPIONNER
		Bukkit.getWorld("world").setBiome(0, 0, Biome.ROOFED_FOREST);
		Bukkit.getWorld("world").getWorldBorder().reset();
		
		configManager = new ConfigManager(this);
		profileManager = new ProfileManager(this);
		
		configManager.loadConfigs();
		profileManager.loadProfiles();
		
		new UHCCmd(this);
		new SpawnCmd(this);
		new RoleCmd(this);
		
		PluginManager pluginManager = getServer().getPluginManager();
		
		
		// Events  listeners
		pluginManager.registerEvents(new JoinQuitListener(this), this);
		pluginManager.registerEvents(new InventoryListener(this), this);
		pluginManager.registerEvents(new ChatListener(this), this);
//		pluginManager.registerEvents(new ScoreboardEvents(this), this);
	}
	
	public void onDisable() 
	{
		profileManager.saveProfiles();
		configManager.saveConfigs();
		
		System.out.println(" ");
		System.out.println("\u001B[31m  Plugin UHC eteint.\u001B[0m");
		System.out.println(" ");
	}

	public static Logger getPluginLogger()
	{
		return logger;
	}
	
	public ConfigManager getConfigManager() {
		return configManager;
	}
	
	public ProfileManager getProfileManager() {
		return profileManager;
	}
}
