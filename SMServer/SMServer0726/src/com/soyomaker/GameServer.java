package com.soyomaker;

import org.apache.log4j.PropertyConfigurator;

import com.soyomaker.net.NetTransceiver;

public class GameServer {

	private static final String LOG4J_CONFIG = "res/log4j.properties";

	private static final String NETTRANSCEIVER_CONFIG = "res/net.xml";

	private GameServer() {

	}

	public static void main(String[] args) {
		GameServer server = new GameServer();
		server.start();
	}

	public void start() {
		// (1) 配置log4j
		PropertyConfigurator.configure(LOG4J_CONFIG);
		// (2) 配置网络收发器
		NetTransceiver.getInstance().config(NETTRANSCEIVER_CONFIG);
	}

}
