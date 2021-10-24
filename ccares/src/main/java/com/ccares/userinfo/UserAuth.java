package com.ccares.userinfo;

public class UserAuth implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; 
	String selector;
	String validator;
	private CcaresUser ccaresUser;
	
	public UserAuth() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSelector() {
		return selector;
	}
	public void setSelector(String s) {
		selector = s;
	}
	
	public String getValidator() {
		return validator;
	}
	public void setValidator(String s) {
		validator = s;
	}
	
	public CcaresUser getCcaresUser() {
		return ccaresUser;
	}
	
	public void setCcaresUser(CcaresUser user) {
		ccaresUser = user;
	}
	
}

