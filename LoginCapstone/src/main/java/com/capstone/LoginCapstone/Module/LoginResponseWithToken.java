package com.capstone.LoginCapstone.Module;

public class LoginResponseWithToken {

	private String token;
    private Long userid;
    private String username;
    private String role;
    private boolean firstTimeLogin;
    
    
	public LoginResponseWithToken(String token, Long userid, String username, String role, boolean firstTimeLogin) {
		super();
		this.token = token;
		this.userid = userid;
		this.username = username;
		this.role = role;
		this.firstTimeLogin = firstTimeLogin;
	}
	@Override
	public String toString() {
		return "LoginResponseWithToken [token=" + token + ", userid=" + userid + ", username=" + username + ", role="
				+ role + ", firstTimeLogin=" + firstTimeLogin + "]";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}
    
}
