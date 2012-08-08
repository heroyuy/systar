package com.soyomaker.emulator.util;

import com.soyomaker.emulator.net.NetTransceiver;
import com.soyomaker.lang.GameObject;

public class Net {

	private static final String MSG_ID_LOGIN_REGISTER = "101001";

	private static final String MSG_ID_LOGIN_LOGIN = "101002";

	private static final String MSG_ID_PLAYER_CREATE = "102001";

	private static Net instance = new Net();

	public static Net getInstance() {
		return instance;
	}

	private Net() {

	}

	/**
	 * 注册101001
	 * 
	 * @param username
	 * @param password
	 */
	public void register(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_LOGIN_REGISTER);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 登录 101002
	 * 
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_LOGIN_LOGIN);
		msg.putString("username", username);
		msg.putString("password", password);
		NetTransceiver.getInstance().sendMessage(msg);
	}

	/**
	 * 创建角色 102001
	 * 
	 * @param name
	 */
	public void createPlayer(String name) {
		GameObject msg = new GameObject();
		msg.setType(MSG_ID_PLAYER_CREATE);
		msg.putString("name", name);
		NetTransceiver.getInstance().sendMessage(msg);
	}

}
