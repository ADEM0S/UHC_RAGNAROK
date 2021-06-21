package be.teammc.uhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;
import be.teammc.uhc.Variables;

import static be.teammc.uhc.utils.Utils.color;

import java.util.ArrayList;
import java.util.List;

import be.teammc.uhc.commands.setrules.SetRule;
import be.teammc.uhc.commands.startuhc.StartUHC;
import be.teammc.uhc.commands.stopuhc.StopUHC;
import be.teammc.uhc.configs.PlayerConfig;


public class UHCCmd extends Command implements TabExecutor {

	private UHC uhc;
	PlayerConfig playerConfig;
	
	public UHCCmd(UHC uhc) {
		super(uhc, "uhc");
		this.uhc = uhc;
	}

	@Override
	public boolean execute(Player player, String[] args)
	{
		if (args.length == 0) return false;
		
		if (args[0].equalsIgnoreCase("help")) {
			for (String string : Variables.UHC_HELP_MESSAGE) {
				player.sendMessage(color(string));
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("state")) {
			boolean state = Variables.getUhcState();
			if (state) {
				player.sendMessage(color("L'uhc est &aen cours."));
			} else {
				player.sendMessage(color("L'uhc est &ca l'arret."));
			}
			return true;
		}
		
		
		World world = Bukkit.getWorld("world");
		
		if (args[0].equalsIgnoreCase("set")) {
			if (player.hasPermission("admin.permission")){
				new SetRule(args, player);
				return true;
			}
			player.sendMessage("T'as pas la permission.");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("start")) {
			if (player.hasPermission("admin.permission")){
				StartUHC start = new StartUHC(uhc, player, world);
				return start.getErrorCode();
			}
			player.sendMessage("T'as pas la permission.");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("stop")) {
			StopUHC stop = new StopUHC(uhc, player, world);
			return stop.getErrorCode();
		}
		return false;
	}
	

	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] args) {
		
		String mainKeyWords[] = {"start", "stop", "state", "set", "help"};
		String setKeyWords[] = {"border-start","border-end","border-damage", "border-speed", "border-ticks", "border-time",
				"roles", "roles-min-time", "tp-min", "help"};
		
		
		List<String> tabComplete = new ArrayList<>();
		
		if (args.length == 1) {
			for (String keywd : mainKeyWords) {
				if (keywd.startsWith(args[0])) tabComplete.add(keywd);
			}
			return tabComplete;
		}
		if (args[0].equalsIgnoreCase("set")) {
			for (String keywd : setKeyWords) {
				if (keywd.startsWith(args[args.length-1])) tabComplete.add(keywd);
			}
			return tabComplete;
		}
		return tabComplete;
	}
}
