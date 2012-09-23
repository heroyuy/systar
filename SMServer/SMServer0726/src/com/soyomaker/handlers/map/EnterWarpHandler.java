package com.soyomaker.handlers.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.MapEntry;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("enterWarpHandler")
public class EnterWarpHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int mapEnreyId = msg.getInt("mapEnreyId");
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		MapEntry mapEntry = mapData.getMapEntryMap().get(mapEnreyId);
		// (1)检查当前地图是否存在此传送点
		if (mapEntry == null) {
			this.sendMessage(session, msg, false, "当前地图不存在此传送点");
			return;
		}
		// (2)移动主角
		player.setMapId(mapEntry.getTargetMapId());
		player.setX(mapEntry.getTargetX());
		player.setY(mapEntry.getTargetY());
		GameObject msgSent = this.buildPackage(msg);
		msgSent.putInt("mapId", player.getMapId());
		msgSent.putInt("x", player.getX());
		msgSent.putInt("y", player.getY());
		netTransceiver.sendMessage(session, msgSent);
	}

}
