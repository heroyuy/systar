package com.soyomaker.net.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("userSessionManager")
public class UserSessionManager {

	private Map<Long, UserSession> sessionMap = new HashMap<Long, UserSession>();

	public synchronized boolean existsUser(long userId) {
		UserSession ps = sessionMap.get(userId);
		if (ps == null) {
			return false;
		} else {
			return true;
		}

	}

	public synchronized void putUserSession(long userId, UserSession session) {
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

	public Collection<UserSession> getAllUserSession() {
		return sessionMap.values();
	}

}
