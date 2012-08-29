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
		int entryId = msg.getInt("entryId");
		// 检查入口点是否合法（入口必需在当前地图上）
	}

}
