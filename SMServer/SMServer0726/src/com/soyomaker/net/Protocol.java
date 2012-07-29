package com.soyomaker.net;

import com.soyomaker.application.AbstractBean;

/**
 * 通信协议
 * 
 * @author wp_g4
 * 
 */
public class Protocol extends AbstractBean {

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

	public String getNetServiceName() {
		return netServiceName;
	}

	@Override
	public void initialize() {
		this.id = this.getParam("id");
		this.netServiceName = this.getParam("NetService");
		String needLogin = this.getParam("needLogin");
		if (needLogin != null) {
			this.needLogin = needLogin.equalsIgnoreCase("true") ? true : false;
		}
		try {
			String handlerClassName = this.getParam("handler");
			Class<?> handlerClass=Class.forName(handlerClassName);
			this.handler=(IHandler) handlerClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

	public void setNetServiceName(String netServiceName) {
		this.netServiceName = netServiceName;
	}

}
