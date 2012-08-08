package com.soyomaker.net;

import org.apache.mina.core.session.IoSession;

/**
 * 逻辑session。
 * 
 * 
 * @author wp_g4
 * 
 */
public class UserSession {

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
	private long userId = -1;

	public UserSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void disConnect() {
		if (this.ioSession != null) {
			this.ioSession.close(true);
		}
	}

}
