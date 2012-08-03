package com.soyomaker.net;

import com.soyomaker.common.GameObject;

public interface IHandler {

	public void handleMessage(PlayerSession playerSession, GameObject msg);
}
