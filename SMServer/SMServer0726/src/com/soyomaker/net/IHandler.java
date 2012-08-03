package com.soyomaker.net;

import com.soyomaker.data.SMObject;

public interface IHandler {

	public void handleMessage(PlayerSession playerSession, SMObject msg);
}
