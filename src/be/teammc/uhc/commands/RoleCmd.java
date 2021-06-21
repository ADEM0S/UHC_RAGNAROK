package be.teammc.uhc.commands;

import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;
import be.teammc.uhc.managers.ProfileManager;
import static be.teammc.uhc.utils.Utils.color;

public class RoleCmd extends Command {
	
	protected ProfileManager profileManager;
	
	public RoleCmd(UHC uhc) {
		super(uhc, "role");
		profileManager = uhc.getProfileManager();
	}

	@Override
	public boolean execute(Player player, String[] args) {
		if (Variables.TIMER > Variables.ROLES_REVEAL_TIME && Variables.uhc_state) {
			player.sendMessage( color( "Tu es " +  profileManager.getProfile(player.getUniqueId()).getRole().getName() ) );
		} else {
			player.sendMessage( color( "&cCHUUUUUT ON A PAS ENCORE REVELE LES ROLES !!" ) );
		}
		return true;
	}
}
