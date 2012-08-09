package com.soyomaker.handlers.player;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

public class ListPlayerHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int playerId = msg.getInt("playerId");
		//(1)检查
	}
}
