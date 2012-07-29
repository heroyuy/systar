package com.soyomaker.net;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.soyomaker.data.ISMObject;
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
	public void sendMessage(PlayerSession session, ISMObject msg) {

	}

	/**
	 * 分发消息到业务逻辑层
	 * 
	 * @param msg
	 */
	public void dispatchMessage(PlayerSession session, ISMObject msg) {

	}

}
