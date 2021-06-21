package be.teammc.uhc.configs;

import static be.teammc.uhc.utils.Utils.log;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import be.teammc.uhc.UHC;

public abstract class Config extends YamlConfiguration{
	protected UHC uhc;
	protected String name;
	protected File file;
	
	public Config(UHC uhc, String name)
	{
		this.uhc = uhc;
		this.name = name;
		file = new File(uhc.getDataFolder(), name);
	}
	
	public Set<String> getSection(String path){
		ConfigurationSection section = getConfigurationSection(path);
		if (section != null) return section.getKeys(false);
		return new HashSet<>();
	}
	
	private void checkFile()
	{
		if (!file.exists())
		{
			file.getParentFile().mkdirs();
			uhc.saveResource(name, false);
		}
	}
	
	public void loadConfig()
	{
		checkFile();
		try{
			load(file);
			log("Loaded from " + name + ".");
		} catch (InvalidConfigurationException | IOException exception) {
			exception.printStackTrace();
			log("[Error] from " + name + ".");
		}
	}
	
	public void saveConfig()
	{
		checkFile();
		try {
			save(file);
			log("Saved from " + name + ".");
		}
		catch(IOException exception) {
			exception.printStackTrace();
			log("[Error] from " + name + ".");
		}
	}
}
