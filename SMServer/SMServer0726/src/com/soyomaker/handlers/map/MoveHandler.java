package com.soyomaker.handlers.map;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("moveHandler")
public class MoveHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO Auto-generated method stub
		int x = msg.getInt("x");
		int y = msg.getInt("y");
		// (1)检查当前位置是否合法，合法有两种情形
		// a 客户端传来的坐标和服务器上的坐标一致
		// b 客户端传来的坐标和服务器上的坐标不一致但坐标在上次行走序列中
		Collection<Integer> steps = msg.getIntArray("steps");
		// (2)检查行走序列是否合法，不合法的情形
		// a 行走路线越出地图边界
		// b 行走路线上有障碍物(环境障碍、npc(?))
	}

}
