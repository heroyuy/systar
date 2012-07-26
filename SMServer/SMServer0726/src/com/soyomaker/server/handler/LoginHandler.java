package com.soyomaker.server.handler;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.IGObject;
import com.soyomaker.server.session.GUSession;

public class LoginHandler extends AbstractBean implements ILoginHandler {

	// private Logger log=Logger.getLogger(LoginHandler.class);

	private long id = 10000;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public long login(GUSession session, IGObject obj) {
		// TODO 简单的一个登录验证实现
		id++;
		return id;
	}

}
