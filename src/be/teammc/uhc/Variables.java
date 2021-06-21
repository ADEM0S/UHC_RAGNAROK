package be.teammc.uhc;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;


import be.teammc.uhc.framework.Role;
import be.teammc.uhc.managers.ScoreBoardManager;

public class Variables {
	
	public static String UHC_HELP_MESSAGE[] = 
		{"&6 HELP MENU:", "&6 - /uhc start", "&6 - /uhc stop", "&6 - /uhc state", "&6 - le reste"};
	
	public static String UHC_SET_HELP_MESSAGE[] = {
			"&6 SET HELP MENU: &f/uhc set <rule>:value",
			"&d&nRules:",
			"&b - BORDERS :",
			"border-start \nborder-end \nborder-damage \nborder-speed \nborder-ticks \nborder-time (jours avant demarrage de la bourdure)",
			"&b - ROLES",
			"roles",
			"&b - TP",
			"tp-min",
			"&b - HELP",
			"help"};
	
	public static final String ROLE_CHOICE_INV_TITLE = "&6Choix des &crôles.";
	public static final int START_SLOT = 10;
	
	
	public static final String UHC_BEGINNING_TITLE = "&cLa pitié n'a pas sa place ici.";
	public static final String UHC_BEGINNING_SUBTITLE = "Détruisez les tous.";
	public static final int UHC_BEGINNING_TITLE_DURATION_TICKS = 40;

	public static long ROLES_REVEAL_TIME = 1200; // Un jour en secondes
	
	public static int BORDER_BEGINNING_SIZE = 100;
	public static int BORDER_FINAL_SIZE = 50;
	public static int BORDER_DAMAGE = 1;
	public static int BORDER_BLOCKS_PER_SECOND = 1;
	public static int BORDER_TICKS_PER_ITERATION = 5;
	public static int BORDER_TIME_BEFORE_START = 24000*2; // deux jours en ticks
	
	public static long TIMER = 0;
	
	public static int TIMER_TASK_ID;
	
	public static int TP_MIN_DISTANCE = 10;
	
	public static Map<Role, Integer> roles = new Hashtable<Role, Integer>();
	
	public static int setRole(Role role) {
		
		for (Entry<Role, Integer> entry : roles.entrySet()) {
			Role r = entry.getKey();
			if (r == role) {
				if (entry.getValue() == 1) {
					roles.replace(r, 0);
					return 0;
				} else if (entry.getValue() == 0)
					roles.replace(r, 1);
					return 1;
			}
		}
		roles.put(role, 1);
		return 1;
	}
	
	
	public static boolean uhc_state = false;

	public static ScoreBoardManager scoreboardManager;

	public static boolean is_revealed = false;
	
	public static void setUhcState(boolean state) {
		if (state != uhc_state) {
			uhc_state = state;
		}
	}
	
	public static boolean getUhcState() {
		return uhc_state;
	}
}
