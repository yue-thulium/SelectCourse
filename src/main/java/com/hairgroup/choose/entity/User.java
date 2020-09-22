package com.hairgroup.choose.entity;

import java.io.Serializable;

public class User implements Serializable {
	private int u_id;
	private String username;
	private String password;
	private boolean identity;
	
	public User() {
		super();
	}

	public User(int u_id, String username, String password, boolean identity) {
		super();
		this.u_id = u_id;
		this.username = username;
		this.password = password;
		this.identity = identity;
	}

	public User(String username, String password, boolean identity) {
		this.username = username;
		this.password = password;
		this.identity = identity;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", username=" + username + ", password=" + password + ", identity=" + identity
				+ "]";
	}
	
}
