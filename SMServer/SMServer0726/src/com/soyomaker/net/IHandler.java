package com.soyomaker.net;

import com.soyomaker.lang.GameObject;

public interface IHandler {

	public void handleMessage(UserSession playerSession, GameObject msg);
}
