package com.soyomaker;

import org.apache.log4j.Logger;

import com.soyomaker.dao.DaoManager;
import com.soyomaker.dao.GameDataSource;
import com.soyomaker.net.NetTransceiver;

public class GameServer {

	private static Logger logger = Logger.getLogger(GameServer.class);

	/*
	 * private static final String MODEL_CONFIG = "res/model.xml";
	 */

	private static final String DATASOURCE_CONFIG = "res/dataSource.xml";

	private static final String DAO_CONFIG = "res/dao.xml";

	private static final String NETTRANSCEIVER_CONFIG = "res/net.xml";

	private GameServer() {

	}

	public static void main(String[] args) {
		try {
			logger.info("系统准备启动");
			GameServer server = new GameServer();
			server.start();
			logger.info("系统成功");
		} catch (Exception e) {
			logger.error("系统启动失败", e);
		}
	}

	public void start() {
		/*
		// (1) 配置数据模型
		Model.getInstance().config(MODEL_CONFIG);
		*/
		//  配置数据源
		GameDataSource.getInstance().config(DATASOURCE_CONFIG);
		//  配置Dao
		DaoManager.getInatance().config(DAO_CONFIG);
		//  配置网络收发器
		NetTransceiver.getInstance().config(NETTRANSCEIVER_CONFIG);

		logger.debug("server start...");
	}

}
