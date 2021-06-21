package be.teammc.uhc.commands.setrules;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import be.teammc.uhc.Variables;
import be.teammc.uhc.framework.Role;

import static be.teammc.uhc.utils.Utils.color;

public class SetRolesRules {
	public SetRolesRules(String arg, Player player) {
		if (arg.startsWith("roles-min-time")) {
			Variables.ROLES_REVEAL_TIME = Integer.parseInt(arg.substring(arg.indexOf(":") + 1)) * 12000; // Fois le nombre de ticks dans une demi journée
			player.sendMessage("Variables.ROLES_REVEAL_TIME set to " + Variables.ROLES_REVEAL_TIME);
		} else {
			Inventory roleInv = Bukkit.createInventory(null, 27, color(Variables.ROLE_CHOICE_INV_TITLE));
			int slot = Variables.START_SLOT;
			
			for (Role role : Role.values()) {
				roleInv.setItem(slot, role.getIcon());
				slot++;
			}
			
			player.openInventory(roleInv);
		}
	}
}
