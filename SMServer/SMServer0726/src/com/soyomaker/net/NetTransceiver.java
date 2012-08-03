package com.soyomaker.net;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.soyomaker.data.GameObject;
import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

/**
 * 网络收发器。 负责发送和接收消息.
 * 
 * @author wp_g4
 * 
 */
public class NetTransceiver {

	private static NetTransceiver instance = new NetTransceiver();

	private Map<String, INetService> netServiceMap = new HashMap<String, INetService>();

	private Map<String, Protocol> protocolMap = new HashMap<String, Protocol>();

	private Logger log = Logger.getLogger(NetTransceiver.class);

	public static NetTransceiver getInstance() {
		return instance;
	}

	private NetTransceiver() {

	}

	public void config(String configFile) {
		try {
			XMLObject netObject = XMLParser.parse(new File("res/net.xml"));
			// 加载NetService
			XMLObject servicesObject = netObject.getChild("NetServices");
			for (XMLObject serviceObject : servicesObject.getChildList()) {
				String name = serviceObject.getAttribute("name");
				String className = serviceObject.getAttribute("class");
				Class<?> serviceClass = Class.forName(className);
				INetService netService = (INetService) serviceClass
						.newInstance();
				netService.start();
				netServiceMap.put(name, netService);
			}
			// 配置协议
			XMLObject protocolsObject = netObject.getChild("Protocols");
			for (XMLObject protocolObject : protocolsObject.getChildList()) {
				String id = protocolObject.getAttribute("id");
				String netService = protocolObject.getAttribute("netService");
				String handlerName = protocolObject.getAttribute("handler");
				String needLogin = protocolObject.getAttribute("needLogin");
				Protocol protocol = new Protocol();
				protocol.setId(id);
				protocol.setNetService(netServiceMap.get(netService));
				if (handlerName != null) {
					Class<?> handlerClass = Class.forName(handlerName);
					IHandler handler = (IHandler) handlerClass.newInstance();
					protocol.setHandler(handler);
				}
				protocol.setNeedLogin(needLogin.equalsIgnoreCase("true"));
				protocolMap.put(id, protocol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 业务逻辑层发送消息
	 * 
	 * @param msg
	 */
	public void sendMessage(PlayerSession session, GameObject msg) {
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
	public void dispatchMessage(PlayerSession session, GameObject msg) {
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
		if (protocol.isNeedLogin() && !session.isLogin()) {
			log.debug("protocol [" + protocol.getId() + "] need login");
		}
		handler.handleMessage(session, msg);
	}
}
