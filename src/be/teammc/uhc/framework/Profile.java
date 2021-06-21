package be.teammc.uhc.framework;

public class Profile {
	private Role role;
	
	public Profile(Role role){
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
