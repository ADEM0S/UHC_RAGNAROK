package be.teammc.uhc.framework;

import static be.teammc.uhc.utils.Utils.decolor;
import static be.teammc.uhc.framework.RoleItems.SASUKE_ICON;
import static be.teammc.uhc.framework.RoleItems.SAKURA_ICON;
import static be.teammc.uhc.framework.RoleItems.NARUTO_ICON;

import org.bukkit.inventory.ItemStack;

public enum Role {
	SASUKE("&5Sasuke", SASUKE_ICON),
	SAKURA("&dSakura", SAKURA_ICON),
	NARUTO("&6Naruto", NARUTO_ICON);
	
	private String name, rawName;
	private ItemStack icon;
	
	Role(String name, ItemStack icon){
		this.name = name;
		this.rawName = decolor(name);
		this.icon = icon;
	}
	
	public static Role getRoleByName(String name) {
		for (Role role : values()) {
			if (name.equalsIgnoreCase(role.getRawName())) {
				return role;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRawName() {
		return rawName;
	}
	
	public ItemStack getIcon() {
		return icon;
	}
}
