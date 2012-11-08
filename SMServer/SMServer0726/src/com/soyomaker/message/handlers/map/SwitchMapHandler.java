package com.soyomaker.message.handlers.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("switchMapHandler")
public class SwitchMapHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		int npcId = msg.getInt("npcId");
		GameObject msgSent = this.buildPackage(msg);
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		Npc npc = mapData.getNpc(npcId);
		// (1)检查当前地图是否存在此传送点
		if (npc == null || npc.getType() != Npc.TYPE_ENTRY) {
			this.addOperateResultToPackage(msgSent, false, "当前地图不存在此传送点");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		int mapId = npc.getTargetMapId();
		int x = npc.getTargetX();
		int y = npc.getTargetY();
		// (2)移动主角
		this.addOperateResultToPackage(msgSent, true, "切换地图成功");
		this.switchMap(session, msgSent, mapId, x, y);
	}

	public void doPush(UserSession session, GameObject msg) {
		GameObject msgSent = this.buildPackage(msg);
		int mapId = msg.getInt("mapId");
		int x = msg.getInt("x");
		int y = msg.getInt("y");
		this.switchMap(session, msgSent, mapId, x, y);
	}

	private void switchMap(UserSession session, GameObject msgSent, int mapId,
			int x, int y) {
		Player player = session.getUser().getPlayer();
		player.setMapId(mapId);
		player.setX(x);
		player.setY(y);
		msgSent.putInt("mapId", player.getMapId());
		msgSent.putInt("x", player.getX());
		msgSent.putInt("y", player.getY());
		netTransceiver.sendMessage(session, msgSent);
	}

}
