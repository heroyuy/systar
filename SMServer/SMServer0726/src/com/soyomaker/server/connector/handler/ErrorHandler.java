package com.soyomaker.server.connector.handler;

import com.soyomaker.data.GUObject;
import com.soyomaker.data.IGUObject;
import com.soyomaker.server.connector.GUSession;

public class ErrorHandler implements IRequestHandler {

	@Override
	public void handleMessage(GUSession session, IGUObject obj) {
		GUObject msg = new GUObject();
		msg.putString("type", "error");
		msg.putString("content", "unknown command type " + obj.getString("type"));

		session.sendMessage(msg);
	}

}
