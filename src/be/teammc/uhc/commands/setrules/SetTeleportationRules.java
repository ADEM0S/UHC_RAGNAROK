package be.teammc.uhc.commands.setrules;

import org.bukkit.entity.Player;

import be.teammc.uhc.Variables;

public class SetTeleportationRules {
	public SetTeleportationRules(String arg, Player player) {
		if (arg.startsWith("tp-min")) {
			Variables.TP_MIN_DISTANCE = Integer.parseInt(arg.substring(arg.indexOf(":") + 1)); // Cherche la valeur après ":"
			player.sendMessage("Variables.TP_MIN_DISTANCE set to " + Variables.TP_MIN_DISTANCE);
		}
	}
}
