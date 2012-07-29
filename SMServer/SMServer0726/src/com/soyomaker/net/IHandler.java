package com.soyomaker.net;

import com.soyomaker.data.ISMObject;

public interface IHandler {

	public void handleMessage(PlayerSession playerSession, ISMObject msg);
}
