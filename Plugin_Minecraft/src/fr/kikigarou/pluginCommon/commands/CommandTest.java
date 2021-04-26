package fr.kikigarou.pluginCommon.commands;


import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Raider;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import fr.kikigarou.pluginCommon.Quest;
import fr.kikigarou.pluginCommon.Utils;

public class CommandTest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			World world = player.getWorld();
			
			// Cmd de test
			if(cmd.getName().equalsIgnoreCase("test")) {
				player.sendMessage("§eBravo tu es un §9joueur qui lance une quête !");
				
				Quest quest = new Quest("test", 2, world, player);
				
				// Location générée par l'objet Quest
				Location questLocation = quest.getLocation();
				
				// Quête à proximité du joueur
				Location questNearPlayer = player.getLocation().clone();
				questNearPlayer.setX(questNearPlayer.getX() + 1);
				
				questNearPlayer.getBlock().setType(Material.CHEST);
				Block questChest = questNearPlayer.getBlock().getRelative(BlockFace.UP);
				
				ItemStack questCompass = new ItemStack(Material.COMPASS);
				CompassMeta cpMeta = (CompassMeta) questCompass.getItemMeta();
				cpMeta.setDisplayName(quest.getName());
				cpMeta.setLore(Arrays.asList("Rendez-vous","à l'endroit indiqué par","la boussole de Questos !"));
				cpMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
				cpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				questCompass.setItemMeta(cpMeta);
				
				player.setCompassTarget(questNearPlayer);
				player.getInventory().setItemInOffHand(questCompass);
				
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("resetquest")) {
				player.setCompassTarget(world.getSpawnLocation());
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("spawnquestos")) {
				player.sendMessage("§eFaire spawn le pnj Questos !");
				
				Location questosLocation = Utils.getFacingLocationPlayer(player.getLocation()).clone();
				float yawQuestos = (questosLocation.getYaw() + 180) % 360;
				questosLocation.setYaw(yawQuestos);
				questosLocation.setPitch(-5);
				
				Villager questos = (Villager) world.spawnEntity(questosLocation, EntityType.VILLAGER);
				questos.setCustomName("§eQuestos");
				questos.setCustomNameVisible(true);
				questos.setVillagerType(Villager.Type.SAVANNA);
				questos.setProfession(Villager.Profession.NONE);
				questos.setAI(false);
				questos.addPassenger(world.spawnEntity(questosLocation, EntityType.PARROT));
				
				return true;
			}
						
			if(cmd.getName().equalsIgnoreCase("fight")) {
				player.sendMessage("§eCombat !!!!");
				
				Raider raidLeader = (Raider) world.spawnEntity(player.getLocation(), EntityType.PILLAGER);
				raidLeader.setPatrolLeader(true);
				
				Block blockPatrol = player.getWorld().getBlockAt(129, 64, -119);
				raidLeader.setPatrolTarget(blockPatrol);
				raidLeader.setRemoveWhenFarAway(false);
				raidLeader.setTarget(player);
				
				return true;
			}
			
			// Cmd alert avec arguments
			if(cmd.getName().equalsIgnoreCase("alert")) {
				if(args.length == 0) {
					player.sendMessage("la commande alert n'a pas de messages !");
				}
				
				if(args.length >= 1) {
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part + " ");
					}
					
					Bukkit.broadcastMessage("["+ player.getName() + "] "+ bc.toString());
				}
				
				return true;
			}
			
		} else {
			sender.sendMessage("Bravo tu as reussi le test !");
		}
		
		return false;
	}

}
