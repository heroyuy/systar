package com.soyomaker.server.handler;

import com.soyomaker.data.ISMObject;
import com.soyomaker.server.session.SMSession;

public interface IRequestHandler {
	public void handleMessage(SMSession session, ISMObject obj);
}
