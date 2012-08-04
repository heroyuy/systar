package com.soyomaker.emulator.net;

import com.soyomaker.emulator.app.GameConfig;
import com.soyomaker.lang.GameObject;

public class NetTransceiver {

	private static NetTransceiver instance = new NetTransceiver();

	public static NetTransceiver getInstance() {
		return instance;
	}

	private MinaClient minaClient = null;

	private NetTransceiver() {
		String ip = GameConfig.getInstance().getIp();
		int port = GameConfig.getInstance().getPort();
		this.minaClient = new MinaClient(ip, port);
	}

	public void start() {
		this.minaClient.start();
	}

	public void stop() {

	}

	public void sendMessage(GameObject msg) {

	}

	public void dispatchMessage(GameObject msg) {

	}

}
