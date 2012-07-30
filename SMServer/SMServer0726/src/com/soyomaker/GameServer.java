package com.soyomaker;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.soyomaker.model.Model;
import com.soyomaker.net.NetTransceiver;

public class GameServer {

	private static final String LOG4J_CONFIG = "res/log4j.properties";

	private static final String MODEL_CONFIG = "res/model.xml";

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
		// (2) 配置数据模型
		Model.getInstance().config(MODEL_CONFIG);
		// (3) 配置网络收发器
		NetTransceiver.getInstance().config(NETTRANSCEIVER_CONFIG);
		Logger.getLogger(this.getClass()).debug("server start...");
	}

}
