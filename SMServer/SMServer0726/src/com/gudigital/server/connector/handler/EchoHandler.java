package com.gudigital.server.connector.handler;

import com.gudigital.core.GUObject;
import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.GUSession;

public class EchoHandler implements IRequestHandler {
	@Override
	public void handleMessage(GUSession session, IGUObject obj) {
		GUObject msg = new GUObject();
		msg.putString("type", "echo");
		msg.putObject("content", obj);

		session.sendMessage(msg);
	}
}
