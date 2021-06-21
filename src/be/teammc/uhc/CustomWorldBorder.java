package be.teammc.uhc;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitTask;

import static be.teammc.uhc.Variables.uhc_state;

public class CustomWorldBorder {
	private BukkitTask task;
	private int ticks_passed = 0;
//	protected UHC uhc;
	
	public CustomWorldBorder(UHC uhc, int borderSize, int maxBorder, int borderDamage, float blocksPerSecond, int ticksPerIteration, int timeBeforeBorders) {
//		this.uhc = uhc;
		launchBorder(uhc, borderSize, maxBorder, borderDamage, blocksPerSecond, ticksPerIteration, timeBeforeBorders);
	}
	
	private void launchBorder(UHC uhc, int borderSize, int maxBorder, int borderDamage, float blocksPerSecond, int ticksPerIteration, int timeBeforeBorders) {
		WorldBorder worldBorder = Bukkit.getWorld("world").getWorldBorder();
		worldBorder.setCenter(0, 0);
		worldBorder.setSize(borderSize);
		worldBorder.setDamageAmount(borderDamage);
		
		task = Bukkit.getScheduler().runTaskTimer(uhc, () -> {
			
			ticks_passed = ticks_passed + ticksPerIteration;
			
			if (ticks_passed > timeBeforeBorders && worldBorder.getSize() >= maxBorder && uhc_state) {
				worldBorder.setSize(worldBorder.getSize() - blocksPerSecond/ticksPerIteration);
			}
			if (worldBorder.getSize() < maxBorder && uhc_state) {
				Bukkit.getScheduler().cancelTask(task.getTaskId());
			}
			
		}, 0, ticksPerIteration);
	}
}
