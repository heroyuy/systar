package com.soyomaker.model.dao;

import com.soyomaker.model.User;

public interface IUserDao {

	public User getUser(int id);

	public void saveUser(User user);

	public void deleteUser(int id);
}
