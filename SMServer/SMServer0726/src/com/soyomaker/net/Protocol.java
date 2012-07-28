package com.soyomaker.net;

/**
 * 通信协议
 * 
 * @author wp_g4
 * 
 */
public class Protocol {

	/**
	 * 协议ID
	 */
	private String id = null;

	/**
	 * 协议通道
	 */
	private String netServiceName = null;

	/**
	 * 协议处理器
	 */
	private String handlerName = null;

	/**
	 * 协议是否仅在登录后应答
	 */
	private boolean needLogin = true;

	public Protocol(String id, String netServiceName, String handlerName) {
		super();
		this.id = id;
		this.netServiceName = netServiceName;
		this.handlerName = handlerName;
	}

	public Protocol(String id, String netServiceName, String handlerName,
			boolean needLogin) {
		super();
		this.id = id;
		this.netServiceName = netServiceName;
		this.handlerName = handlerName;
		this.needLogin = needLogin;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public String getId() {
		return id;
	}

	public String getNetServiceName() {
		return netServiceName;
	}

	public boolean isNeedLogin() {
		return needLogin;
	}

}
