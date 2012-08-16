package com.soyomaker.handlers.map;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("changeMapHandler")
public class ChangeMapHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO Auto-generated method stub
		int mapId = msg.getInt("mapId");
		int entryId = msg.getInt("entryId");
		// (1) 检查目标地图是否合法（只有相邻地图可以移动）
		// (2) 检查入口点是否合法（入口必需在当前地图上）
	}

}
