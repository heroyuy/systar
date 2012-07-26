package com.soyomaker.server.handler;

import com.soyomaker.data.IGObject;
import com.soyomaker.server.session.GUSession;

public interface ILoginHandler {
	/**
	 * 登录
	 * 
	 * @param session
	 * @param obj
	 * @return 用户ID
	 */
	public long login(GUSession session, IGObject obj);

}
