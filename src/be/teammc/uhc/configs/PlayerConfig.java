package be.teammc.uhc.configs;

import java.util.UUID;

import be.teammc.uhc.UHC;
import be.teammc.uhc.framework.Role;

public class PlayerConfig extends Config{

	public PlayerConfig(UHC uhc) {
		super(uhc, "players.yml");
	}
	
	public Role getRole(UUID id) {
		return Role.getRoleByName(getString(id.toString() + ".class"));
	}
	
	public void setRole(UUID id, Role role) {
		set(id.toString() + ".class", role.getRawName());
	}
}
