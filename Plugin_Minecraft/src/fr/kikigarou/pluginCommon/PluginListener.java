package fr.kikigarou.pluginCommon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;

public class PluginListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		player.getInventory().clear();
		
		ItemStack customsword = new ItemStack(Material.IRON_SWORD, 1);
		ItemMeta customM = customsword.getItemMeta();
		customM.setDisplayName("Excaliburne");
		customM.setLore(Arrays.asList("Epée utilisée par le légendaire", "Eanderprout !!!!", "Magnifique !!!"));
		customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customsword.setItemMeta(customM);

		player.getInventory().setItemInMainHand(customsword);
		player.updateInventory();
	}
	
	@EventHandler
	public void onInteractWithEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity entity = event.getRightClicked();
		
		if(entity instanceof Villager && entity.getCustomName().equals("§eQuestos")) {
			Bukkit.broadcastMessage("HI, i'm Questos !");			
			Quest quest = new Quest("test", 2, player.getWorld(), player);
			
			ItemStack questCompass = new ItemStack(Material.COMPASS);
			CompassMeta cpMeta = (CompassMeta) questCompass.getItemMeta();
			cpMeta.setDisplayName(quest.getName());
			cpMeta.setLore(Arrays.asList("Rendez-vous","à l'endroit indiqué par","la boussole de Questos !"));
			cpMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
			cpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			questCompass.setItemMeta(cpMeta);
			
			Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, "Quêtes");
			inv.setItem(0, questCompass);
			player.openInventory(inv);
		}
	}
	
	 @EventHandler
	 public void zombieDeath (EntityDeathEvent event) {
		 if (!event.getEntityType().equals(EntityType.ZOMBIE)) {
	            return;
	        }
	        if (event.getEntity().getKiller() == null) {
	            return;
	        }
	        LivingEntity entity = event.getEntity();
	        Location location = entity.getLocation();
	        Player player = entity.getKiller();
	        int looting_mod = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS);
	        double luck_mod = player.getAttribute(Attribute.GENERIC_LUCK).getValue();

	        LootContext.Builder builder = new LootContext.Builder(location);
	        builder.lootedEntity(event.getEntity());
	        builder.lootingModifier(looting_mod);
	        builder.luck((float) luck_mod);
	        builder.killer(player);
	        LootContext lootContext = builder.build();

	        ZombieLoot zombieLoot = new ZombieLoot();
	        Collection<ItemStack> drops = zombieLoot.populateLoot(new Random(), lootContext);
	        ArrayList<ItemStack> items = (ArrayList<ItemStack>) drops;

	        event.getDrops().clear();

	        for (int a = 0; a < 2; a++) {
	            if (items.get(a).getAmount() > 0) {
	                location.getWorld().dropItemNaturally(location, items.get(a));
	            }
	        }
	    }

}
