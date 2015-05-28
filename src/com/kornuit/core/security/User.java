package com.kornuit.core.security;

public class User {
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	private String username, remoteAddress;

	public User(String username, String remoteAddress) {
		super();
		this.username = username;
		this.remoteAddress = remoteAddress;
	}
	

}
