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

	public static final String TYPE_RESPONSE_AND_PUSH = "ResponseAndPush";

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
		if (type.equalsIgnoreCase(TYPE_RESPONSE_ONLY)) {
			this.type = TYPE_RESPONSE_ONLY;
		} else if (type.equalsIgnoreCase(TYPE_PUSH_ONLY)) {
			this.type = TYPE_PUSH_ONLY;
		} else if (type.equalsIgnoreCase(TYPE_RESPONSE_AND_PUSH)) {
			this.type = TYPE_RESPONSE_AND_PUSH;
		} else {
			throw new IllegalArgumentException(
					"\""
							+ type
							+ "\" is illegal protocol type,protocol type must be one of \""
							+ TYPE_RESPONSE_ONLY + "\"、\"" + TYPE_PUSH_ONLY
							+ "\"、\"" + TYPE_RESPONSE_AND_PUSH + "\"");
		}
	}

	/**
	 * 协议是否可推送
	 * 
	 * @return
	 */
	public boolean isPushable() {
		return this.type.equals(Protocol.TYPE_PUSH_ONLY)
				|| this.type.equals(Protocol.TYPE_RESPONSE_AND_PUSH);
	}

	/**
	 * 协议是否可应答
	 * 
	 * @return
	 */
	public boolean isRespondent() {
		return this.type.equals(Protocol.TYPE_RESPONSE_ONLY)
				|| this.type.equals(Protocol.TYPE_RESPONSE_AND_PUSH);
	}

	public String toString() {
		return "id:" + id + " type:" + type + " netService:" + netService
				+ " needLogin:" + needLogin + " handler:" + handler;
	}

}
