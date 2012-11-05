package com.soyomaker.net;

/**
 * 通信协议
 * 
 * @author wp_g4
 * 
 */
public class Protocol {

	public static final String TYPE_RESPONSE_ONLY = "ResponseOnly";

	public static final String TYPE_PUSH_ONLY = "PushOnly";

	public static final String TYPE_PUSH_AND_RESPONSE = "PushAndResponse";

	/**
	 * 协议ID
	 */
	private String id = null;

	/**
	 * 协议类型
	 */
	private String type = TYPE_RESPONSE_ONLY;

	/**
	 * 协议通道
	 */
	private INetService netService = null;

	/**
	 * 协议处理器
	 */
	private IHandler handler = null;

	/**
	 * 协议是否仅在登录后应答
	 */
	private boolean needLogin = true;

	public IHandler getHandler() {
		return handler;
	}

	public String getId() {
		return id;
	}

	public INetService getNetService() {
		return netService;
	}

	public String getType() {
		return type;
	}

	public boolean isNeedLogin() {
		return needLogin;
	}

	public void setHandler(IHandler handler) {
		this.handler = handler;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNeedLogin(boolean needLogin) {
		this.needLogin = needLogin;
	}

	public void setNetService(INetService netService) {
		this.netService = netService;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return "id:" + id + " type:" + type + " netService:" + netService
				+ " needLogin:" + needLogin + " handler:" + handler;
	}

}
