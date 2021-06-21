package be.teammc.uhc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import be.teammc.uhc.UHC;
import be.teammc.uhc.framework.Profile;
import be.teammc.uhc.framework.Role;
import be.teammc.uhc.managers.ProfileManager;

import static be.teammc.uhc.utils.Utils.color;

public class ChatListener implements Listener {
	private ProfileManager profileManager;
	
	public ChatListener(UHC uhc) {
		profileManager = uhc.getProfileManager();
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());
		Role role = profile.getRole();
		event.setCancelled(true);
		if (role == null) { // On ne le veut pas dans notre cas, c'est que pour le RPG
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(color("&8[&fSans Role&8] &f" + player.getName() + " &7>> &f" + event.getMessage()));
			}
		} else {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(color("&8[&f" + role.getName() +  "&8] &f" + player.getName() + " &7>> &f" + event.getMessage()));
			}
		}
	}
}
