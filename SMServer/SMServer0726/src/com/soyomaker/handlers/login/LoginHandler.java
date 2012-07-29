package com.soyomaker.handlers.login;

import org.apache.log4j.Logger;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.PlayerSession;

public class LoginHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, ISMObject msg) {
		Logger log = Logger.getLogger(getClass());
		log.debug("loginMessage:" + msg);

	}

}
