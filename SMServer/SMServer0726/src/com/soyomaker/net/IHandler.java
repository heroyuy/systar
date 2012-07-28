package com.soyomaker.net;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.PlayerSession;

public interface IHandler {

	public void handleMessage(PlayerSession playerSession, ISMObject msg);
}
