package com.soyomaker.dao.impl;

import java.util.List;

import com.soyomaker.dao.BasicDao;
import com.soyomaker.model.User;

public class UserDao extends BasicDao<User> {

	public User getUser(String username) {
		List<User> list = this.get("username", "=", username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
