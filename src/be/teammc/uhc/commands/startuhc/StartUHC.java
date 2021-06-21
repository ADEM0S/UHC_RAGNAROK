package be.teammc.uhc.commands.startuhc;

import static be.teammc.uhc.utils.Utils.color;
import static be.teammc.uhc.utils.Utils.decolor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import be.teammc.uhc.CustomWorldBorder;
import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;
import be.teammc.uhc.framework.Profile;
import be.teammc.uhc.framework.Role;
import be.teammc.uhc.managers.ProfileManager;
import be.teammc.uhc.managers.ScoreBoardManager;
import be.teammc.uhc.utils.TitleManager;

public class StartUHC {
	private boolean error;
	private ProfileManager profileManager;
	private UHC uhc;
	private Player player;
	private World world;
	
	public StartUHC(UHC uhc, Player player, World world) {
		this.uhc = uhc;
		profileManager = uhc.getProfileManager();
		this.player = player;
		this.world = world;
		error = start();
	}
	
	private boolean start() {
		if (!distributeRoles()) {
			player.sendMessage(color("&4Le nombre de Joueurs n'est pas le même que le nombre de Roles."));
			player.sendMessage(color("&4Fais attention au nombre de roles attribués."));
			return false;
		}
		
		world.setGameRuleValue("naturalRegeneration", "false"); // Regen off
		
		for (Player p : world.getPlayers()) // Gros message de début
		{
			TitleManager.sendTitle(p, color(Variables.UHC_BEGINNING_TITLE), decolor(Variables.UHC_BEGINNING_SUBTITLE), Variables.UHC_BEGINNING_TITLE_DURATION_TICKS);
		}
		
		new CustomWorldBorder(uhc, Variables.BORDER_BEGINNING_SIZE*2, Variables.BORDER_FINAL_SIZE*2, 
										Variables.BORDER_DAMAGE, Variables.BORDER_BLOCKS_PER_SECOND, 
										Variables.BORDER_TICKS_PER_ITERATION, Variables.BORDER_TIME_BEFORE_START); // (UHC uhc, int borderSize, int maxBorder, int borderDamage, float blocksPerSecond, int ticksPerIteration)
		teleportEverybody();
		
		// Juste un petit bail
		player.sendMessage(color("UHC commence grace a &d"+ player.getDisplayName()));
		Variables.setUhcState(true);
		startTimer();
		
		Variables.scoreboardManager = new ScoreBoardManager(uhc);
		Variables.scoreboardManager.createInGameManager();
		
		return true;
	}
	
	private void startTimer() {
		Variables.TIMER_TASK_ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(uhc, new Runnable() {
			
			@Override
			public void run() {
				Variables.TIMER ++;
				
				if (Variables.TIMER >= Variables.ROLES_REVEAL_TIME && Variables.uhc_state && !Variables.is_revealed) {
					Variables.is_revealed = true;
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage(color( "Tu es " +  profileManager.getProfile(player.getUniqueId()).getRole().getName() )); // Changer pour un texte spécial en fonction de la classe (dans la définition du Role même)
					}
				}
			}
			
		}, 1, 20);
	}

	private boolean distributeRoles() {
		List<Role> listed_roles = listRoles();
		if (listed_roles.size() != Bukkit.getOnlinePlayers().size()) {
			return false;
		}
		
		List<Player> players = Bukkit.getWorld("world").getPlayers();
		Collections.shuffle(players);
		
		for (int i = 0; i < players.size(); i++) {
			Profile profile = profileManager.getProfile(players.get(i).getUniqueId());
			profile.setRole(listed_roles.get(i));	
		}
		return true;
	}
	
	private List<Role> listRoles() {
		List<Role> listedRoles = new ArrayList<Role>();
		for (Entry<Role, Integer> entry : Variables.roles.entrySet()) {
			for (int j = 0; j < entry.getValue(); j++) {
				listedRoles.add(entry.getKey());
			}
		}
		return listedRoles;
	}
	
	
	private void teleportEverybody() {
		List<Player> players = world.getPlayers();
		int x,y,z;
		for (Player player : players) {
			x = new Random().nextInt( Variables.BORDER_BEGINNING_SIZE - Variables.TP_MIN_DISTANCE) + Variables.TP_MIN_DISTANCE;
			z = new Random().nextInt( Variables.BORDER_BEGINNING_SIZE - Variables.TP_MIN_DISTANCE) + Variables.TP_MIN_DISTANCE;
			Location loc = new Location(world, x, 0, z);
			
			y = world.getHighestBlockYAt(loc);
			loc.setY(y);
			player.teleport(loc);
		}
	}

	public boolean getErrorCode() {
		return error;
	}
}
