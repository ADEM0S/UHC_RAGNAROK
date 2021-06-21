package be.teammc.uhc.managers;

import static be.teammc.uhc.utils.Utils.color;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;

public class ScoreBoardManager {
	protected UHC uhc;
	protected int inGameManagerTaskID;
	
	public ScoreBoardManager(UHC uhc) {
		this.uhc = uhc;
	}
	
	public void deleteInGameManager() {
		Bukkit.getScheduler().cancelTask(inGameManagerTaskID);
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		}
	}
	
	public void createInGameManager() {
		inGameManagerTaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(uhc, new Runnable() {
			
			@Override
			public void run() {
				String timerString = transformTimer();
				for (Player p : Bukkit.getOnlinePlayers()) {
					final Player player = p;
					final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
					
					Objective o = scoreboard.registerNewObjective("test", "dummy");
					o.setDisplaySlot(DisplaySlot.SIDEBAR);
					o.setDisplayName(color("&c&l&nUHC RAGNAROK"));
					
					List<Player> playersAlive = getPlayersAlive();
					
					Score gap = o.getScore("");
					gap.setScore(11);
					
					Score aliveCount = o.getScore(color("&cJoueurs : &a" + playersAlive.size()));
					aliveCount.setScore(10);
					
					Score separator = o.getScore(color("&0---------------- "));
					separator.setScore(9);
					
					Score timer = o.getScore(color("&aTimer : &f" + timerString));
					timer.setScore(8);
					
					Score health = o.getScore("Vie : " + Math.round(player.getHealth()));
					health.setScore(7);
					
					player.setScoreboard(scoreboard);
				}
			}
			
		}, 1, 10);
	}
	
	private String transformTimer() {
		long seconds = Variables.TIMER;
		int hours = (int) seconds / 3600;
		
		seconds = seconds - hours*3600;
		int minutes = (int) seconds/60;
		
		seconds = seconds - minutes*60;
		
		String formattedTimer = "";
		
		if (minutes < 10) {
			formattedTimer = "0" + hours + "h";
		} else {
			formattedTimer = hours + "h";
		}
		
		if (minutes < 10) {
			formattedTimer = formattedTimer + "0" + minutes + "m";
		} else {
			formattedTimer = formattedTimer + "" + minutes + "m";
		}
		
		if (seconds < 10) {
			formattedTimer = formattedTimer + "0" + seconds + "s";
		} else {
			formattedTimer = formattedTimer + "" + seconds + "s";
		}
		
		return formattedTimer;
	}
	
	private List<Player> getPlayersAlive() {
		List<Player> alivePlayers = new ArrayList<Player>();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getGameMode() == GameMode.SURVIVAL && p.getHealth() > 0) {
				alivePlayers.add(p);
			}
		}
		return alivePlayers;
	}
}
