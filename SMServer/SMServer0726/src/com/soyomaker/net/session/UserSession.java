package com.soyomaker.net.session;

import org.apache.mina.core.session.IoSession;

import com.soyomaker.model.User;

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

	private User user;


	public UserSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void disConnect() {
		if (this.ioSession != null) {
			this.ioSession.close(true);
		}
	}

}
