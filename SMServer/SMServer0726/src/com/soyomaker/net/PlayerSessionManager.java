package com.soyomaker.net;

import java.util.HashMap;
import java.util.Map;

public class PlayerSessionManager {

	private static PlayerSessionManager instance = new PlayerSessionManager();

	public static PlayerSessionManager getInstance() {
		return instance;
	}

	private Map<Long, PlayerSession> playerSessionMap = new HashMap<Long, PlayerSession>();

	private PlayerSessionManager() {

	}

	public synchronized void putPlayerSession(long userId,
			PlayerSession playerSession) {
		PlayerSession ps = this.getPlayerSession(userId);
		if (ps != null && ps != playerSession) {
			ps.disConnect();
			this.removePlayerSession(userId);
		}
		this.playerSessionMap.put(userId, playerSession);
	}

	public synchronized PlayerSession getPlayerSession(long userId) {
		return playerSessionMap.get(userId);
	}

	public synchronized void removePlayerSession(long userId) {
		this.playerSessionMap.remove(userId);
	}

}
