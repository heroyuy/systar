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
	public void handleMessage(UserSession session, GameObject msg) {
		int npcId = msg.getInt("npcId");
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		Npc npc = mapData.getNpc(npcId);
		// (1)检查当前地图是否存在此传送点
		if (npc == null || npc.getType() != Npc.TYPE_ENTRY) {
			this.sendMessage(session, msg, false, "当前地图不存在此传送点");
			return;
		}
		// (2)移动主角
		player.setMapId(npc.getTargetMapId());
		player.setX(npc.getTargetX());
		player.setY(npc.getTargetY());
		GameObject msgSent = this.buildPackage(msg, true, "成功");
		msgSent.putInt("mapId", player.getMapId());
		msgSent.putInt("x", player.getX());
		msgSent.putInt("y", player.getY());
		netTransceiver.sendMessage(session, msgSent);
	}

}
