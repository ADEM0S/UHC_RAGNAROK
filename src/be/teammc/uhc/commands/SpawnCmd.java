package be.teammc.uhc.commands;

import static be.teammc.uhc.utils.Utils.color;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;

public class SpawnCmd extends Command {

	public SpawnCmd(UHC uhc) {
		super(uhc, "spawn");
	}

	@Override
	public boolean execute(Player player, String[] args) {
		
		if (args.length > 0 && args[0].equalsIgnoreCase("O")) {
			
			player.sendMessage(color("&6Tu retournes &cau spawn."));
			player.teleport( new Location(player.getWorld(), 0.5 , 249.1, 0.5 ) );
			return true;
		}
		
		return false;
	}
}
