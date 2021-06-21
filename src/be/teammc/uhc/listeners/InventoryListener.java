package be.teammc.uhc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;
import be.teammc.uhc.framework.Role;
//import be.teammc.uhc.managers.ProfileManager;
import static be.teammc.uhc.utils.Utils.color;

public class InventoryListener implements Listener {
	
//	private UHC uhc;
//	private ProfileManager profileManager;
	
	public InventoryListener(UHC uhc) {
//		this.uhc = uhc;
//		profileManager = uhc.getProfileManager();
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		if (!event.getView().getTitle().equalsIgnoreCase( color(Variables.ROLE_CHOICE_INV_TITLE ))) {
			return;
		}
		event.setCancelled(true);
		int slot = event.getRawSlot();
		Player player = (Player) event.getWhoClicked();
		Role[] roles = Role.values();
		if (slot < Variables.START_SLOT || slot > Variables.START_SLOT + roles.length) return;
		int roleIndex = slot - Variables.START_SLOT;
		Role selected = roles[roleIndex];
		
		int amount = Variables.setRole(selected);
		
		if (amount == 0) {
			player.sendMessage(color(selected.getName() + " &cdeselectionne."));
			return;
		}
		player.sendMessage(color(selected.getName() + " &aselectionne."));		
	}
}
