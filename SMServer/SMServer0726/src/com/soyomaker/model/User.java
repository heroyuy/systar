package com.soyomaker.model;

import org.nutz.dao.entity.annotation.*;

@Table("user")
public class User {

	@Column
	@Id
	private long id;

	@Column
	@Name
	private String username;

	@Column
	private String password;

	public long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
