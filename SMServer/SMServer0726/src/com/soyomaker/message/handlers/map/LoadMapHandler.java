package com.soyomaker.message.handlers.map;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("loadMapHandler")
public class LoadMapHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Autowired
	private MessagePusher messagePusher;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		GameObject msgSent = this.buildPackage(msg);
		Collection<GameObject> mapEntryObjList = new ArrayList<GameObject>();
		for (Npc npc : mapData.getNpcList()) {
			GameObject mapEntryObj = new GameObject();
			mapEntryObj.putInt("id", npc.getId());
			mapEntryObjList.add(mapEntryObj);
		}
		msgSent.putObjectArray("npcList", mapEntryObjList);
		netTransceiver.sendMessage(session, msgSent);
		// 触发更新NPC状态
		messagePusher.updateNPCStatus(session);
	}

}
