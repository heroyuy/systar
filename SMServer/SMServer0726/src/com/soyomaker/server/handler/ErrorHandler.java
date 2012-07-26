package com.soyomaker.server.handler;

import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;
import com.soyomaker.server.session.GUSession;

public class ErrorHandler implements IRequestHandler {

	@Override
	public void handleMessage(GUSession session, IGObject obj) {
		GObject msg = new GObject();
		msg.putString("type", "error");
		msg.putString("content", "unknown command type " + obj.getString("type"));

		session.sendMessage(msg);
	}

}
