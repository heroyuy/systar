package com.soyomaker.net;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.UserSession;

public interface IHandler {

	public void handleMessage(UserSession playerSession, ISMObject msg);
}
