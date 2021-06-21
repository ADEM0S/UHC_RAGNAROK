package be.teammc.uhc.utils;

import static be.teammc.uhc.utils.Utils.color;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemsCreation {
	
	public static ItemStack createItem(Material material, int amount, boolean glow, boolean unb, String name, String... lore) {
		ItemStack item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		if (name != null) {
			meta.setDisplayName(color(name));
		}
		if (lore != null) {
			List<String> list = new ArrayList<>();
			for ( String string : lore) {
				list.add(color(string));
			}
			meta.setLore(list);
		}
		if (glow) {
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			meta.addEnchant(Enchantment.DURABILITY, 1, true);
		}
		if (unb) {
			meta.spigot().setUnbreakable(true);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	
}
