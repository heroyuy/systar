package com.soyomaker.server.connector.handler;

import com.soyomaker.core.GUObject;
import com.soyomaker.core.IGUObject;
import com.soyomaker.server.connector.GUSession;

public class EchoHandler implements IRequestHandler {
	@Override
	public void handleMessage(GUSession session, IGUObject obj) {
		GUObject msg = new GUObject();
		msg.putString("type", "echo");
		msg.putObject("content", obj);

		session.sendMessage(msg);
	}
}
