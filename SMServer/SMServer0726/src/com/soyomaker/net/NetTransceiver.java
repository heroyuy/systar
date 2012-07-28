package com.soyomaker.net;

import com.soyomaker.data.ISMObject;

/**
 * 网络收发器。 负责发送和接收消息.
 * 
 * @author wp_g4
 * 
 */
public class NetTransceiver {

	private static NetTransceiver instance = new NetTransceiver();

	public static NetTransceiver getInstance() {
		return instance;
	}

	private NetTransceiver() {

	}

	public void config(String configFile) {

	}

	/**
	 * 业务逻辑层发送消息
	 * 
	 * @param msg
	 */
	public void sendMessage(ISMObject msg) {

	}

	/**
	 * 分发消息到业务逻辑层
	 * 
	 * @param msg
	 */
	public void dispatchMessage(ISMObject msg) {

	}

}
