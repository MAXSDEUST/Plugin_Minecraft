package fr.kikigarou.pluginCommon;

import org.bukkit.Location;

public class Utils {
	
	public static Location getFacingLocationPlayer(Location playerLocation) {
		Location facingLocation = playerLocation.clone();
		float rotat = facingLocation.getYaw();
		
		// SOUTH
		if(rotat < 45.00 || rotat > 315.00) {
			facingLocation.setZ(facingLocation.getZ() + 1);
		}
		
		// NORTH
		if(rotat > 135.00 && rotat < 225.00) {
			facingLocation.setZ(facingLocation.getZ() - 1);
		}
		
		// WEST
		if(rotat < 315.00 && rotat > 225.00) {
			facingLocation.setX(facingLocation.getX() + 1);
		}
		
		// EAST
		if(rotat > 45.00 && rotat < 135.00) {
			facingLocation.setX(facingLocation.getX() - 1);
		}
		
		return facingLocation;
	}

}
