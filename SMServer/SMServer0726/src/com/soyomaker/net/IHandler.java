package com.soyomaker.net;

import com.soyomaker.data.GameObject;

public interface IHandler {

	public void handleMessage(PlayerSession playerSession, GameObject msg);
}
