package com.soyomaker.net.session;

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

	public UserSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

}
