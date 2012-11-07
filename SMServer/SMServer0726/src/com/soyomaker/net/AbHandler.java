package com.soyomaker.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.session.UserSession;

@Component
public class AbHandler implements IHandler {

	public static String SN_KEY = "sn";

	@Autowired
	protected NetTransceiver netTransceiver;

	/**
	 * 根据收到的包构造回复包，初始化回复包的协议ID
	 * 
	 * @param msgReceived
	 *            收到的包
	 */
	public GameObject buildPushPackage(GameObject msgReceived) {
		GameObject msgSent = new GameObject();
		msgSent.setType(msgReceived.getType());
		return msgSent;
	}

	/**
	 * 根据收到的包构造回复包，初始化回复包的序列号和协议ID
	 * 
	 * @param msgReceived
	 *            收到的包
	 */
	public GameObject buildResponsePackage(GameObject msgReceived) {
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
	public GameObject buildResponsePackage(GameObject msgReceived,
			boolean status, String message) {
		GameObject msgSent = this.buildResponsePackage(msgReceived);
		msgSent.putBool("res", status);
		msgSent.putString("msg", message);
		return msgSent;
	}

	@Override
	public boolean checkPushPackage(GameObject msg) {
		return true;
	}

	@Override
	public boolean checkRequestPackage(GameObject msg) {
		return true;
	}

	@Override
	public void doPush(UserSession session, GameObject msg) {

	}

	@Override
	public void doRequest(UserSession session, GameObject msg) {

	}

	/**
	 * 发送普通消息
	 * 
	 * @param session
	 *            会话
	 * @param msgReceived
	 *            收到的包
	 * @param status
	 *            操作状态
	 * @param message
	 *            操作信息
	 */
	public void sendResponseMessage(UserSession session,
			GameObject msgReceived, boolean status, String message) {
		netTransceiver.sendMessage(session,
				this.buildResponsePackage(msgReceived, status, message));
	}

}
