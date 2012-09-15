package com.soyomaker.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;

@Component
public abstract class AbHandler implements IHandler {

	public static String SN_KEY = "sn";

	@Autowired
	protected NetTransceiver netTransceiver;

	/**
	 * 根据收到的包构造回复包，初始化回复包的序列号和协议ID
	 * 
	 * @param msgReceived
	 *            收到的包
	 */
	public GameObject buildPackage(GameObject msgReceived) {
		GameObject msgSent = new GameObject();
		msgSent.setType(msgReceived.getType());
		msgSent.putInt(SN_KEY, msgReceived.getInt(SN_KEY));
		return msgSent;
	}

	/**
	 * 
	 * 根据收到的包构造回复包，初始化回复包的序列号、协议ID、操作状态、操作信息
	 * 
	 * @param msgReceived
	 *            收到的包
	 * @param status
	 *            操作状态
	 * @param message
	 *            操作信息
	 */
	public GameObject buildPackage(GameObject msgReceived, boolean status,
			String message) {
		GameObject msgSent = this.buildPackage(msgReceived);
		msgSent.putBool("res", status);
		msgSent.putString("msg", message);
		return msgSent;
	}

	public void sendMessage(UserSession session, GameObject msgReceived,
			boolean status, String message) {
		netTransceiver.sendMessage(session,
				this.buildPackage(msgReceived, status, message));
	}
}
