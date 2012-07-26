package com.soyomaker.server.handler;

import com.soyomaker.data.IGObject;
import com.soyomaker.server.session.GUSession;

public interface IRequestHandler {
	public void handleMessage(GUSession session, IGObject obj);
}
