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
	 * 构造待发包，包括协议ID、序列号（如果存在）
	 * 
	 * @param msg
	 *            收到包，request或者push
	 * @return 待发包
	 */
	public GameObject buildPackage(GameObject msg) {
		GameObject msgSent = new GameObject();
		msgSent.setType(msg.getType());
		if (msg.containsKey(SN_KEY)) {
			msgSent.putInt(SN_KEY, msg.getInt(SN_KEY));
		}
		return msgSent;
	}

	/**
	 * 添加操作结果到待发包
	 * 
	 * @param msgSent
	 *            待发包
	 * @param status
	 *            操作状态
	 * @param message
	 *            操作描述
	 */
	public void addOperateResultToPackage(GameObject msgSent, boolean status,
			String message) {
		msgSent.putBool("res", status);
		msgSent.putString("msg", message);
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

}
