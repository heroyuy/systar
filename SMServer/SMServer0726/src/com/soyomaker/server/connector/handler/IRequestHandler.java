package com.soyomaker.server.connector.handler;

import com.soyomaker.core.IGUObject;
import com.soyomaker.server.connector.GUSession;

public interface IRequestHandler {
	public void handleMessage(GUSession session, IGUObject obj);
}
