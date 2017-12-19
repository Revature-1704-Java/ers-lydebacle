package com.revature.REIMBURSEAPP;

public class User {
	private int userid;
	private String username;
	private boolean isManagerYup = false;
	public User() {};
	public User(int uID) {
		this.userid = uID;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserID() {
		return this.userid;
	}
	public boolean isManager() {
		return isManagerYup;
	}
}
