package com.soyomaker.net;

import com.soyomaker.lang.GameObject;

public abstract class AbHandler implements IHandler {

	/**
	 * 创建基本包
	 * 
	 * @param protocolId
	 *            协议ID
	 * @return 基本包
	 */
	public GameObject buildPackage(String protocolId) {
		GameObject msgSent = new GameObject();
		msgSent.setType(protocolId);
		return msgSent;
	}

	/**
	 * 创建基本包
	 * 
	 * @param protocolId
	 *            协议ID
	 * @param status
	 *            状态
	 * @param message
	 *            附带消息
	 * @return 基本包
	 */
	public GameObject buildPackage(String protocolId, boolean status,
			String message) {
		GameObject msgSent = new GameObject();
		msgSent.setType(protocolId);
		msgSent.putBool("status", status);
		msgSent.putString("msg", message);
		return msgSent;
	}

	public void sendMessage(UserSession session, String protocolId,
			boolean status, String message) {
		NetTransceiver.getInstance().sendMessage(session,
				this.buildPackage(protocolId, status, message));
	}
}
