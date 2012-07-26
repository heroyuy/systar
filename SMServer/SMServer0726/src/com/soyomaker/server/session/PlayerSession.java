package com.soyomaker.server.session;

import java.util.Date;

import com.soyomaker.data.GObject;

/**
 * 玩家会话。玩家会话是从用户login开始，到logout（或者断开物理连接）结束。 这是一个逻辑的过程，而不是物理上的。
 * 依照不同的底层，类型有：MINA_TYPE, HTTP_TYPE
 * 
 * @author zhangjun
 * 
 */
public class PlayerSession {
	private long playerId;
	private Date loginTime;
	private GObject palyerVars = new GObject();

	private GUSession session;

	public Date getLoginTime() {
		return loginTime;
	}

	public GObject getPalyerVars() {
		return palyerVars;
	}

	public long getPlayerId() {
		return playerId;
	}

	public GUSession getSession() {
		return session;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setPalyerVars(GObject palyerVars) {
		this.palyerVars = palyerVars;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public void setSession(GUSession session) {
		this.session = session;
	}

}
