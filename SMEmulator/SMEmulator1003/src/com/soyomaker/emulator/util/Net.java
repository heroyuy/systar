package com.soyomaker.emulator.util;

import com.soyomaker.emulator.net.NetTransceiver;
import com.soyomaker.lang.GameObject;

public class Net {

	private static final String MSG_ID_REGISTER = "101001";
	
	private static final String MSG_ID_LOGIN = "101002";

	private static Net instance = new Net();

	public static Net getInstance() {
		return instance;
	}

	private Net() {

	}

	public void register(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_REGISTER);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	public void login(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_LOGIN);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

}
