package com.soyomaker.message.handlers.npc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("npcDialogueHandler")
public class NPCDialogueHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int npcId = msg.getInt("npcId");
		MapData mapData = dictManager.getMapData(session.getUser().getPlayer()
				.getMapId());
		// (1)检查NPC是否存在
		if (mapData.hasNpc(npcId)) {
			this.sendMessage(session, msg, false, "当前地图上不存在此NPC");
			return;
		}
		// (2)检查NPC是否可以对话
		Npc npc = mapData.getNpc(npcId);
		if (npc.getType() != Npc.TYPE_NORMAL) {
			this.sendMessage(session, msg, false, "此NPC不能对话");
			return;
		}
		//(3)
	}

}
