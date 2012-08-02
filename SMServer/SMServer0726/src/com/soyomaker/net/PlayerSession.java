package com.soyomaker.net;

import org.apache.mina.core.session.IoSession;

/**
 * 逻辑session。
 * 
 * 
 * @author wp_g4
 * 
 */
public class PlayerSession {

	/**
	 * 网络层session
	 */
	private IoSession ioSession = null;

	/**
	 * 是否登录
	 */
	private boolean login = false;

	/**
	 * 玩家id
	 */
	private int playerId = -1;

	public PlayerSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public int getPlayerId() {
		return playerId;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void disConnect() {
		if (this.ioSession != null) {
			this.ioSession.close(true);
		}
	}

}
