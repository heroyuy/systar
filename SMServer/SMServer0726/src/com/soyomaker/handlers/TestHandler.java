package com.soyomaker.handlers;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("testHandler")
public class TestHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO Auto-generated method stub
		netTransceiver.sendMessage(session, msg);
	}

}
