package fr.kikigarou.pluginCommon.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			Location spawn = new Location(player.getWorld(), 129.869, 64, -119.743, 1.8f, 7.4f);
			player.sendMessage("§eBienvenue au spawn !!!");
			player.teleport(spawn);
		}
		
		return false;
	}

}
