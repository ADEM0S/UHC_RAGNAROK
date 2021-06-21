package be.teammc.uhc.commands.setrules;

import org.bukkit.entity.Player;

import be.teammc.uhc.Variables;

public class SetBorderRules {
	
	public SetBorderRules(String arg, Player player) {
		applyBorderSetters(arg, player);
	}

	private void applyBorderSetters(String arg, Player player) {
		if (arg.startsWith("border-start")) {
			Variables.BORDER_BEGINNING_SIZE = Integer.parseInt(arg.substring(arg.indexOf(":") + 1)); // Cherche la valeur après ":"
			player.sendMessage("Variables.BORDER_BEGINNING_SIZE set to " + Variables.BORDER_BEGINNING_SIZE);
		}
		if (arg.startsWith("border-end")) {
			Variables.BORDER_FINAL_SIZE= Integer.parseInt(arg.substring(arg.indexOf(":") + 1));
			player.sendMessage("Variables.BORDER_FINAL_SIZE set to " + Variables.BORDER_FINAL_SIZE);
		}
		if (arg.startsWith("border-damage")) {
			Variables.BORDER_DAMAGE= Integer.parseInt(arg.substring(arg.indexOf(":") + 1));
			player.sendMessage("Variables.BORDER_DAMAGE set to " + Variables.BORDER_DAMAGE);
		}
		if (arg.startsWith("border-speed")) {
			Variables.BORDER_BLOCKS_PER_SECOND= Integer.parseInt(arg.substring(arg.indexOf(":") + 1));
			player.sendMessage("Variables.BORDER_BLOCKS_PER_SECOND set to " + Variables.BORDER_BLOCKS_PER_SECOND);
		}
		if (arg.startsWith("border-ticks")) {
			Variables.BORDER_TICKS_PER_ITERATION = Integer.parseInt(arg.substring(arg.indexOf(":") + 1));
			player.sendMessage("Variables.BORDER_TICKS_PER_ITERATION set to " + Variables.BORDER_TICKS_PER_ITERATION);
		}
		if (arg.startsWith("border-time")) {
			Variables.BORDER_TIME_BEFORE_START = Integer.parseInt(arg.substring(arg.indexOf(":") + 1)) * 24000;
			player.sendMessage("Variables.BORDER_TIME_BEFORE_START set to " + Variables.BORDER_TIME_BEFORE_START);
		}
	}
}
