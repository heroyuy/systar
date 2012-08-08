package com.soyomaker.net;

import java.util.HashMap;
import java.util.Map;

public class UserSessionManager {

	private static UserSessionManager instance = new UserSessionManager();

	public static UserSessionManager getInstance() {
		return instance;
	}

	private Map<Long, UserSession> sessionMap = new HashMap<Long, UserSession>();

	private UserSessionManager() {

	}

	public synchronized void putUserSession(long userId,
			UserSession session) {
		UserSession ps = this.getUserSession(userId);
		if (ps != null && ps != session) {
			ps.disConnect();
			this.removeUserSession(userId);
		}
		this.sessionMap.put(userId, session);
	}

	public synchronized UserSession getUserSession(long userId) {
		return sessionMap.get(userId);
	}

	public synchronized void removeUserSession(long userId) {
		this.sessionMap.remove(userId);
	}

}
