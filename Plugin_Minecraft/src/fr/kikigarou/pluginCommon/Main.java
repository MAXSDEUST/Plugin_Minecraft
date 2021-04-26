package fr.kikigarou.pluginCommon;

import org.bukkit.plugin.java.JavaPlugin;

import fr.kikigarou.pluginCommon.commands.CommandTest;
import fr.kikigarou.pluginCommon.commands.Commands;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		System.out.println("Demarrage du plugin !");
		
		saveDefaultConfig();
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
		getCommand("fight").setExecutor(new CommandTest());
		getCommand("resetquest").setExecutor(new CommandTest());
		getCommand("spawnquestos").setExecutor(new CommandTest());
		getCommand("spawn").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("Arret du plugin !");
	}
}
