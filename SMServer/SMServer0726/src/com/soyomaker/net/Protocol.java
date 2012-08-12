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

	public String toString() {
		return "id:" + id + " netService:" + netService + " needLogin:"
				+ needLogin + " handler:" + handler;
	}

}
