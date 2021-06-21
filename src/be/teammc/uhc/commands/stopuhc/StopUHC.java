package be.teammc.uhc.commands.stopuhc;

import static be.teammc.uhc.utils.Utils.color;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;

public class StopUHC {
	private boolean error;
//	private UHC uhc;
	private Player player;
	private World world;
	
	public StopUHC(UHC uhc, Player player, World world) {
//		this.uhc = uhc;
		this.player = player;
		this.world = world;
		error = stop();
	}
	private boolean stop() {
		if (player.hasPermission("admin.permission")){
			world.setGameRuleValue("naturalRegeneration", "true");
			
			world.getWorldBorder().reset();
			
			player.sendMessage(color("UHC arrete par &d"+ player.getDisplayName()));
			Variables.setUhcState(false);
			
			Bukkit.getScheduler().cancelTask(Variables.TIMER_TASK_ID);
			Variables.TIMER = 0;
			Variables.is_revealed = false;
			
			Variables.scoreboardManager.deleteInGameManager();
			return true;
		}
		player.sendMessage("T'as pas la permission.");
		return true;
	}
	
	public boolean getErrorCode() {
		return error;
	}
}
