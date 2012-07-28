package com.soyomaker.net.handler;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.SMSession;

public class LoginHandler extends AbstractBean {

	private long id = 10000;

	public void initialize() {

	}

	public long login(SMSession session, ISMObject obj) {
		// TODO 简单的一个登录验证实现
		id++;
		return id;
	}

}
