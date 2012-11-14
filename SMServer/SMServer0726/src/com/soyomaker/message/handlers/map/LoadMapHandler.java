package com.soyomaker.message.handlers.map;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.message.util.NpcUtil;
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

	@Autowired
	private NpcUtil npcUtil;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		GameObject msgSent = this.buildPackage(msg);
		Collection<GameObject> mapEntryObjList = new ArrayList<GameObject>();
		for (Npc npc : mapData.getNpcList()) {
			mapEntryObjList.add(npcUtil.convertNpc(npc));
		}
		msgSent.putObjectArray("npcList", mapEntryObjList);
		netTransceiver.sendMessage(session, msgSent);
		// 触发更新NPC状态
		messagePusher.updateNpcState(session);
	}

}
