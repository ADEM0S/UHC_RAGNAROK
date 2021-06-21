package be.teammc.uhc.commands.setrules;

import org.bukkit.entity.Player;

import be.teammc.uhc.Variables;
import static be.teammc.uhc.utils.Utils.color;

public class SetRulesHelp {
	public SetRulesHelp(Player player) {
		for (String msg : Variables.UHC_SET_HELP_MESSAGE) {
			player.sendMessage(color(msg));
		}
	}
}
