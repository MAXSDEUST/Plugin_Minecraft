package fr.kikigarou.pluginCommon;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Quest {
	
	private String name;
	private int reward;
	private Location location;
	
	// Constructor
	public Quest(String name, int reward, World world, Player player) {
		this.name = name;
		this.reward = reward;
		this.location = this.generateRandomLocation(world, player.getLocation().getX(), player.getLocation().getZ());
	}
	
	// GETTERS
	public String getName() {
		return name;
	}
	
	public int getReward() {
		return reward;
	}
	
	public Location getLocation() {
		return location;
	}
	
	
	// SETTERS
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setReward(int newReward) {
		this.reward = newReward;
	}
	
	public void setLocation(Location newLocation) {
		this.location = newLocation;
	}
	
	// Others Functions
	public Location generateRandomLocation(World world, double x, double z) {
		double randomX = new Random().nextInt(1000 + 25) - 25;
		double randomZ = new Random().nextInt(1000 + 25) - 25;
		
		x = x + randomX;
		z = z + randomZ;
		int y = world.getHighestBlockYAt((int) x, (int) z);
		
		return new Location(world, x, y, z);
	}
}
