package be.teammc.uhc.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;

public abstract class Command implements CommandExecutor {

	protected UHC uhc;
	protected String name;
	
	public Command(UHC uhc, String name)
	{
		this.uhc = uhc;
		uhc.getCommand(name).setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] args) {
		boolean exit = true;
		if (sender instanceof Player) {
			exit = execute((Player) sender, args);
		}
		return exit;
	}
	
	public abstract boolean execute(Player player, String[] args);
}
