package kr.co.bookking.util;

public class Role {
	private String[] roles = {"admin", "root", "manager"};
	private static Role role;
	
	public static Role getInstance() {
		if (role == null) {
			role = new Role();
		}
		
		return role;
	}

	public String[] getRoles() {
		return roles;
		// 객체를 복제해서 리턴하도록 수정
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		Role.role = role;
	}
	
	public String adminList() {
		String result = "";
		for(String role : roles) {
			result += role + " ";
		}
		return result;
	}
}
