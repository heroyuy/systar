package com.soyomaker.net.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.application.IService;

public class PlayerSessionManager extends AbstractBean implements IService {
	private Map<Long, PlayerSession> playerSessions = new HashMap<Long, PlayerSession>();
	private List<SessionListener> sessionListeners = new ArrayList<SessionListener>();

	public void addSessionListener(SessionListener listener) {
		sessionListeners.add(listener);
	}

	public PlayerSession createSession(long playerId) {
		PlayerSession session = new PlayerSession();
		session.setPlayerId(playerId);

		PlayerSession pSession = playerSessions.get(playerId);
		if (pSession != null) {
			pSession.getSession().disconnect(); // 如果用户已经登录，则断开连接
		}

		playerSessions.put(playerId, session);

		for (SessionListener l : sessionListeners) {
			l.onSessionCreated(session);
		}

		return session;
	}

	public PlayerSession getPlayerSession(long id) {
		return playerSessions.get(id);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	public void removePlayerSession(long id) {
		PlayerSession s = playerSessions.get(id);
		if (s != null) {
			playerSessions.remove(id);
			for (SessionListener l : sessionListeners) {
				l.onSessionRemoved(s);
			}
			s.getSession();
		}
	}

	public void removeSessionListener(SessionListener listener) {
		sessionListeners.remove(listener);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
