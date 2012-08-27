package com.soyomaker.handlers.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.PlayerService;

@Component("moveEndHandler")
public class MoveEndHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO Auto-generated method stub
		int x = msg.getInt("x");
		int y = msg.getInt("y");
		// 检查坐标是否在上次行走序列中

	}
}
