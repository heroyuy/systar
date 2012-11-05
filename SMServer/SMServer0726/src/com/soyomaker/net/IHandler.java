package com.soyomaker.net;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.session.UserSession;

public interface IHandler {

	public void handleMessage(UserSession session, GameObject msg);
}
