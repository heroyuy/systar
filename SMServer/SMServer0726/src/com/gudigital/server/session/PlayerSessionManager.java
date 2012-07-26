package com.gudigital.server.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gudigital.application.AbstractBean;
import com.gudigital.application.IService;
import com.gudigital.core.IGUObject;

public class PlayerSessionManager extends AbstractBean implements IService {
	private Map<Long, PlayerSession> playerSessions = new HashMap<Long, PlayerSession>();
	private List<ISessionListener> sessionListeners = new ArrayList<ISessionListener>();

	public void addSessionListener(ISessionListener listener) {
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

		for (ISessionListener l : sessionListeners) {
			l.onSessionCreated(session);
		}

		return session;
	}

	@Override
	public void doCommand(IGUObject command) {
		// TODO Auto-generated method stub

	}

	public PlayerSession getPlayerSession(long id) {
		return playerSessions.get(id);
	}

	@Override
	public IGUObject getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	public void removePlayerSession(long id) {
		PlayerSession s = playerSessions.get(id);
		if (s != null) {
			playerSessions.remove(id);
			for (ISessionListener l : sessionListeners) {
				l.onSessionRemoved(s);
			}
			s.getSession();
		}
	}

	public void removeSessionListener(ISessionListener listener) {
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
