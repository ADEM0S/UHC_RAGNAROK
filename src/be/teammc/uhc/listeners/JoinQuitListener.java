package be.teammc.uhc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import be.teammc.uhc.UHC;
import be.teammc.uhc.framework.Profile;
import be.teammc.uhc.managers.ProfileManager;

public class JoinQuitListener implements Listener
{
//	private UHC uhc;
	private ProfileManager profileManager;
	
	// Constructeur
	public JoinQuitListener(UHC uhc) 
	{
//		this.uhc = uhc;
		profileManager = uhc.getProfileManager();
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void playerJoin(PlayerJoinEvent event) 
	{
		
		Player player = event.getPlayer();
		
		
		Profile profile = profileManager.getProfile(player.getUniqueId());
		if (profile == null) {
			profile = profileManager.createProfile(player);
		}
		
		
		event.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.RESET + " a rejoint le serveur!");
		player.sendMessage("Amuse toi bien bg ;-)");
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void playerQuit(PlayerQuitEvent event)
	{
		
	}
}
