package be.teammc.uhc.managers;

import java.util.ArrayList;
import java.util.List;

import be.teammc.uhc.UHC;
import be.teammc.uhc.configs.Config;
import be.teammc.uhc.configs.PlayerConfig;

public class ConfigManager {

//	private UHC uhc;
	private List<Config> configs = new ArrayList<>();
	private PlayerConfig playerConfig;
	
	public ConfigManager(UHC uhc)
	{
//		this.uhc = uhc;
		configs.add(playerConfig = new PlayerConfig(uhc));
	}
	
	public void loadConfigs() 
	{
		for (Config config : configs)
		{
			config.loadConfig();
		}
	}
	
	public void saveConfigs() 
	{
		for (Config config : configs)
		{
			config.saveConfig();
		}
	}
	
	public PlayerConfig getPlayerConfig()
	{
		return playerConfig;
	}
}
