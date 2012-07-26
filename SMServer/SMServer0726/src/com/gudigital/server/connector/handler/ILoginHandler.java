package com.gudigital.server.connector.handler;

import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.GUSession;

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
