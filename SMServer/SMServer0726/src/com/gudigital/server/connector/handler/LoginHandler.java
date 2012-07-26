package com.gudigital.server.connector.handler;

import com.gudigital.application.AbstractBean;
import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.GUSession;

public class LoginHandler extends AbstractBean implements ILoginHandler {

	// private Logger log=Logger.getLogger(LoginHandler.class);

	private long id = 10000;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public long login(GUSession session, IGUObject obj) {
		// TODO 简单的一个登录验证实现
		id++;
		return id;
	}

}
