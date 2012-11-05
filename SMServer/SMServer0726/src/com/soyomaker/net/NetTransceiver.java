package com.soyomaker.net;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.session.UserSession;

/**
 * 网络收发器。 负责发送和接收消息.
 * 
 * @author wp_g4
 * 
 */
public class NetTransceiver {

	private static final Logger log = Logger.getLogger(NetTransceiver.class);

	private Map<String, Protocol> protocolMap = new HashMap<String, Protocol>();

	private NetTransceiver() {

	}

	/**
	 * 业务逻辑层发送消息
	 * 
	 * @param msg
	 */
	public void sendMessage(UserSession session, GameObject msg) {
		String id = msg.getType();
		Protocol protocol = protocolMap.get(id);
		// (1)检查是否有对应的协议
		if (protocol == null) {
			log.debug("unknow protocol message");
			return;
		}
		// (2)检查协议是否有netService处理
		INetService netService = protocol.getNetService();
		if (netService == null) {
			log.debug("no netService for this message");
			return;
		}
		netService.sendMessage(session, msg);
	}

	/**
	 * 分发消息到业务逻辑层
	 * 
	 * @param msg
	 */
	public void dispatchMessage(UserSession session, GameObject msg) {
		String id = msg.getType();
		Protocol protocol = protocolMap.get(id);
		// (1)检查是否有对应的协议
		if (protocol == null) {
			log.debug("unknow protocol message");
			return;
		}
		// (2)检查协议是否有handler处理
		IHandler handler = protocol.getHandler();
		if (handler == null) {
			log.debug("no handler for this message");
			return;
		}
		// (3)检查协议是否允许
		if (protocol.isNeedLogin() && session.getUser() == null) {
			log.debug("protocol [" + protocol.getId() + "] need login");
		} else {
			handler.handleMessage(session, msg);
		}
	}
}
