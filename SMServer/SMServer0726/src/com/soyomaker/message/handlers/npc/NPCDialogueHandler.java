package com.soyomaker.message.handlers.npc;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("npcDialogueHandler")
public class NPCDialogueHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int npcId = msg.getInt("npcId");
	}

}
