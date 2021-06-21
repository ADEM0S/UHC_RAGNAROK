package be.teammc.uhc.commands.setrules;

import org.bukkit.entity.Player;

public class SetRule {
	
	public SetRule(String[] args, Player player) {
		for (String arg : args) {
			if (arg.startsWith("border")) {
				new SetBorderRules(arg, player);
			}
			if (arg.startsWith("roles")) {
				new SetRolesRules(arg, player);
			}
			if (arg.startsWith("tp")) {
				new SetTeleportationRules(arg, player);
			}
			
			if (arg.startsWith("help")) {
				new SetRulesHelp(player);
			}
		}
	}

}
