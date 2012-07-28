package com.soyomaker.net.session;

import java.util.Date;

import com.soyomaker.data.SMObject;

/**
 * 玩家会话。玩家会话是从用户login开始，到logout（或者断开物理连接）结束。 这是一个逻辑的过程，而不是物理上的。
 * 依照不同的底层，类型有：MINA_TYPE, HTTP_TYPE
 * 
 * @author wp_g4
 * 
 */
public class PlayerSession {
	private long playerId;
	private Date loginTime;
	private SMObject palyerVars = new SMObject();

	private SMSession session;

	public Date getLoginTime() {
		return loginTime;
	}

	public SMObject getPalyerVars() {
		return palyerVars;
	}

	public long getPlayerId() {
		return playerId;
	}

	public SMSession getSession() {
		return session;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public void setPalyerVars(SMObject palyerVars) {
		this.palyerVars = palyerVars;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public void setSession(SMSession session) {
		this.session = session;
	}

}
