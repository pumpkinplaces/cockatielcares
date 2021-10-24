package com.ccares.userinfo;


public class CcaresUser implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String username; 
	private String password;
	private String email;
	private String profilePic; 
	private int id;
	
	public CcaresUser() {
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String s) {
		username = s;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String s) {
		password = s;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String s) {
		email = s;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String s) {
		profilePic = s;
	}
	
	
}

