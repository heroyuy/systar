package com.soyomaker.net.session;

/**
 * 玩家会话监听接口，是逻辑层使用的。
 * 
 * @author wp_g4
 * 
 */
public interface SessionListener {
	/**
	 * 用户会话建立（login）
	 * 
	 * @param session
	 */
	public void onSessionCreated(PlayerSession session);

	/**
	 * 用户会话删除（logout）
	 * 
	 * @param session
	 */
	public void onSessionRemoved(PlayerSession session);
}
