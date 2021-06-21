package be.teammc.uhc.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import be.teammc.uhc.UHC;
import be.teammc.uhc.configs.PlayerConfig;
import be.teammc.uhc.framework.Profile;
import be.teammc.uhc.framework.Role;

public class ProfileManager {
//	private UHC uhc;
	private PlayerConfig playerConfig;
	private Map<UUID, Profile> profiles = new HashMap<>();
	
	public ProfileManager(UHC uhc) {
//		this.uhc = uhc;
		playerConfig = uhc.getConfigManager().getPlayerConfig();
	}
	
	
	public void loadProfiles() {
		for (String key : playerConfig.getSection("")) {
			UUID uuid = UUID.fromString(key);
			Role role = playerConfig.getRole(uuid);
			Profile  profile = new Profile(role);
			profiles.put(uuid, profile);
		}
	}
	
	public void saveProfiles() {
		for (UUID uuid : profiles.keySet()) {
			Profile profile = getProfile(uuid);
			playerConfig.setRole(uuid, profile.getRole());
		}
	}
	
	
	public Profile createProfile(Player player) {
		Profile profile = new Profile(null);
		profiles.put(player.getUniqueId(), profile);
		return profile;
	}
	
	public Profile getProfile(UUID uuid) {
		return profiles.get(uuid);
	}
}
