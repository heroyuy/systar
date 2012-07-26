package com.gudigital.server.connector.handler;

import com.gudigital.core.IGUObject;
import com.gudigital.server.connector.GUSession;

public interface IRequestHandler {
	public void handleMessage(GUSession session, IGUObject obj);
}
