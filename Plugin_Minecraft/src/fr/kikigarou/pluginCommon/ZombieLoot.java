package fr.kikigarou.pluginCommon;

import java.util.Collection;
import java.util.Random;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.Plugin;

public class ZombieLoot implements LootTable {

	private Plugin plugin = Bukkit.getPluginManager().getPlugin("MCQuestos");
    private NamespacedKey key = new NamespacedKey(plugin, "zombie_loot");
    private Collection<ItemStack> items = new ArrayList<ItemStack>();

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        double looting_modifier;
        double luck_modifier;

        int looting_level = context.getLootingModifier();
        if (looting_level > 0) {
            looting_modifier = (Math.pow(looting_level, 0.8) + 2) / 2;
        }
        else {
            looting_modifier = 1;
        }

        double luck_level = context.getLuck();
        if (luck_level > 0) {
            luck_modifier = Math.pow(luck_level, 1.5) / 3;
        }
        else if (luck_level == 0){
            luck_modifier = 1;
        }
        else {
            luck_modifier = 0;
        }
        int random_modifier = random.nextInt(5);
        int item_count = (int) (looting_modifier * luck_modifier * random_modifier);

        int leather_count;
        int flesh_count;

        if (item_count > 0) {
            leather_count = random.nextInt(item_count / 3);
            flesh_count = item_count - leather_count;
        }
        else {
            leather_count = 0;
            flesh_count = 0;
        }



        ItemStack rotten_flesh = new ItemStack(Material.ROTTEN_FLESH);
        ItemStack leather = new ItemStack(Material.LEATHER);

        items.add(rotten_flesh);
        items.add(leather);

        return items;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
     
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
