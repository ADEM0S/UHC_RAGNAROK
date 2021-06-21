package be.teammc.uhc.framework;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static be.teammc.uhc.utils.ItemsCreation.createItem;

public class RoleItems {
	
	public static final ItemStack SASUKE_ICON = createItem(Material.DIAMOND_CHESTPLATE, 1, true, true, "&5&oSasuke", "Armure de protection ultime."),
			SAKURA_ICON = createItem(Material.STICK, 1, true, true, "&d&oSakura", "Wola elle fait mal"),
			NARUTO_ICON = createItem(Material.EYE_OF_ENDER, 1, true, true, "&6&oNaruto", "RAZENGAAAN.");
}
