package com.soyomaker.net;

import java.util.HashMap;
import java.util.Map;

public class PlayerSessionManager {

	private static PlayerSessionManager instance = new PlayerSessionManager();

	public static PlayerSessionManager getInstance() {
		return instance;
	}

	private Map<Integer, PlayerSession> playerSessionMap = new HashMap<Integer, PlayerSession>();

	private PlayerSessionManager() {

	}

	public synchronized void putPlayerSession(int playerId,
			PlayerSession playerSession) {
		PlayerSession ps = this.getPlayerSession(playerId);
		if (ps != null) {
			ps.disConnect();
			this.removePlayerSession(playerId);
		}
		this.playerSessionMap.put(playerId, playerSession);
	}

	public synchronized PlayerSession getPlayerSession(int playerId) {
		return playerSessionMap.get(playerId);
	}

	public synchronized void removePlayerSession(int playerId) {
		this.playerSessionMap.remove(playerId);
	}

}
