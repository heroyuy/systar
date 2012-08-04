package com.soyomaker.emulator.net;

import com.soyomaker.lang.GameObject;

public class MinaClientTest implements NetListener {

	private MinaClient mc = null;

	public MinaClientTest() {
		mc = new MinaClient("localhost", 9090);
		mc.setListener(this);
		mc.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MinaClientTest mct = new MinaClientTest();
	}

	@Override
	public void onMessageReceived(GameObject msg) {
		System.out.println("收到:" + msg.toJson());
	}

	@Override
	public void onConnected() {
		System.out.println("连接成功");
		this.login();
	}

	@Override
	public boolean onDisconnected() {
		System.out.println("连接断开");
		return false;
	}

	public void register() {
		GameObject msg = new GameObject();
		msg.setType("101001");
		msg.putString("username", "wp_g4");
		msg.putString("password", "2724504");
		this.mc.sendMessage(msg);
	}

	public void login() {
		GameObject msg = new GameObject();
		msg.setType("101002");
		msg.putString("username", "wp_g4");
		msg.putString("password", "2724504");
		this.mc.sendMessage(msg);
	}

}
