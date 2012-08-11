package com.soyomaker.dao;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.dao.impl.UserDao;
import com.soyomaker.model.User;
import com.soyomaker.model.Player;

public class DaoManager {

	private static DaoManager inatance = new DaoManager();

	public static DaoManager getInatance() {
		return inatance;
	}

	private Map<Class<?>, IDao<?>> daoMap = new HashMap<Class<?>, IDao<?>>();

	private DaoManager() {

	}

	private <T> void addDao(Class<T> modelClass, IDao<T> dao) {
		this.daoMap.put(modelClass, dao);
	}

	@SuppressWarnings("unchecked")
	public <T> IDao<T> getDao(Class<T> modelClass) {
		return (IDao<T>) this.daoMap.get(modelClass);
	}

	public void config(String configFile) {
		// User
		this.addDao(User.class, new UserDao());
		// Player
		this.addDao(Player.class, new BasicDao<Player>() {
		});
	}
}
