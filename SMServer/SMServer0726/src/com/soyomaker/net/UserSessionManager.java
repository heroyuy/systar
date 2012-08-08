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

	public synchronized void putPlayerSession(long userId,
			UserSession playerSession) {
		UserSession ps = this.getPlayerSession(userId);
		if (ps != null && ps != playerSession) {
			ps.disConnect();
			this.removePlayerSession(userId);
		}
		this.sessionMap.put(userId, playerSession);
	}

	public synchronized UserSession getPlayerSession(long userId) {
		return sessionMap.get(userId);
	}

	public synchronized void removePlayerSession(long userId) {
		this.sessionMap.remove(userId);
	}

}
