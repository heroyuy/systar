package com.soyomaker.server.connector.handler;

import com.soyomaker.data.IGUObject;
import com.soyomaker.server.connector.GUSession;

public interface ILoginHandler {
	/**
	 * 登录
	 * 
	 * @param session
	 * @param obj
	 * @return 用户ID
	 */
	public long login(GUSession session, IGUObject obj);

}
