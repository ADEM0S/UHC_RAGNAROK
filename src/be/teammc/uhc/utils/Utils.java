package be.teammc.uhc.utils;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;

public class Utils {
	private static Logger logger = UHC.getPluginLogger();
	
	public static String color(String str) 
	{
		return ChatColor.translateAlternateColorCodes( '&', str );
	}
	
	public static String decolor(String str)
	{
		return ChatColor.stripColor(str);
	}
	
	public static void log(String... strings)
	{
		for (String string : strings)
		{
			logger.info(string);
		}
	}
	
	public static void msgPlayer(Player player, String... strings)
	{
		for (String string : strings)
		{
			player.sendMessage(color(string));
		}
	}
}
