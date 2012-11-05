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
	 * 分发收到的客户端消息到业务逻辑层
	 * 
	 * @param session
	 *            会话
	 * @param msg
	 *            消息
	 */
	public void dispatchMessage(UserSession session, GameObject msg) {
		String id = msg.getType();
		Protocol protocol = protocolMap.get(id);
		// (1)检查是否有对应的协议
		if (protocol == null) {
			log.debug("unknow protocol message");
			return;
		}
		// (2)检查协议是否是应答协议
		if (protocol.getType() != Protocol.TYPE_RESPONSE_ONLY
				&& protocol.getType() != Protocol.TYPE_PUSH_AND_RESPONSE) {
			log.debug("protocol [" + protocol.getId()
					+ "] is not a response protocol");
			return;
		}
		// (3)检查协议是否有handler处理
		IHandler handler = protocol.getHandler();
		if (handler == null) {
			log.debug("no handler for this message");
			return;
		}
		// (4)检查协议是否允许
		if (protocol.isNeedLogin() && session.getUser() == null) {
			log.debug("protocol [" + protocol.getId() + "] need login");
			return;
		}
		handler.handleMessage(session, msg);
	}

	/**
	 * 推送消息到业务逻辑层
	 * 
	 * @param session
	 *            会话
	 * @param msg
	 *            消息
	 */
	public void pushMessage(UserSession session, GameObject msg) {
		String id = msg.getType();
		Protocol protocol = protocolMap.get(id);
		// (1)检查是否有对应的协议
		if (protocol == null) {
			log.debug("unknow protocol message");
			return;
		}
		// (2)检查协议是否是推送协议
		if (protocol.getType() != Protocol.TYPE_PUSH_ONLY
				&& protocol.getType() != Protocol.TYPE_PUSH_AND_RESPONSE) {
			log.debug("protocol [" + protocol.getId()
					+ "] is not a push protocol");
			return;
		}
		// (3)检查协议是否有handler处理
		IHandler handler = protocol.getHandler();
		if (handler == null) {
			log.debug("no handler for this message");
			return;
		}
		handler.handleMessage(session, msg);
	}

	public Map<String, Protocol> getProtocolMap() {
		return protocolMap;
	}

	/**
	 * 业务逻辑层向客户端发消息
	 * 
	 * @param session
	 *            会话
	 * @param msg
	 *            消息
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

	public void setProtocolMap(Map<String, Protocol> protocolMap) {
		this.protocolMap = protocolMap;
	}
}
