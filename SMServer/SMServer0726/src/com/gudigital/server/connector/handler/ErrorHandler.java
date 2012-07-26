package com.gudigital.server.connector.handler;

import com.gudigital.core.GUObject;
import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.GUSession;

public class ErrorHandler implements IRequestHandler {

	@Override
	public void handleMessage(GUSession session, IGUObject obj) {
		GUObject msg = new GUObject();
		msg.putString("type", "error");
		msg.putString("content", "unknown command type " + obj.getString("type"));

		session.sendMessage(msg);
	}

}
