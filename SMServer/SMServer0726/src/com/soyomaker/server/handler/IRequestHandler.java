package com.soyomaker.server.handler;

import com.soyomaker.data.ISMObject;
import com.soyomaker.server.session.GUSession;

public interface IRequestHandler {
	public void handleMessage(GUSession session, ISMObject obj);
}
