package com.soyomaker.net.handler;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.SMSession;

public interface IRequestHandler {
	public void handleMessage(SMSession session, ISMObject obj);
}
