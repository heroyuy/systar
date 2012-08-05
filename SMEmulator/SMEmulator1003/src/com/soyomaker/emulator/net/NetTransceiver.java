package com.soyomaker.emulator.net;

import com.soyomaker.emulator.app.GameConfig;
import com.soyomaker.lang.GameObject;

public class NetTransceiver {

	private static NetTransceiver instance = new NetTransceiver();

	public static NetTransceiver getInstance() {
		return instance;
	}

	private MinaClient minaClient = null;

	private IHandler handler = null;

	public IHandler getHandler() {
		return handler;
	}

	public void setHandler(IHandler handler) {
		this.handler = handler;
	}

	private NetTransceiver() {
		String ip = GameConfig.getInstance().getIp();
		int port = GameConfig.getInstance().getPort();
		this.minaClient = new MinaClient(ip, port);
	}

	public void start() {
		this.minaClient.connect();
	}

	public void stop() {
		this.minaClient.disconnect();
	}

	public void sendMessage(GameObject msg) {
		this.minaClient.sendMessage(msg);
	}

	public void dispatchMessage(GameObject msg) {
		if (this.handler != null) {
			this.handler.onMessageReceived(msg);
		}
	}

}
