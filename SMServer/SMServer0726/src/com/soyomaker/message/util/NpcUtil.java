package com.soyomaker.message.util;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Npc;

@Component("npcUtil")
public class NpcUtil {

	public GameObject convertNpc(Npc npc) {
		GameObject npcObj = new GameObject();
		npcObj.putInt("id", npc.getId());
		npcObj.putString("name", npc.getName());
		npcObj.putInt("type", npc.getType());
		npcObj.putInt("mapId", npc.getMapId());
		npcObj.putInt("col", npc.getCol());
		npcObj.putInt("row", npc.getRow());
		npcObj.putInt("targetMapId", npc.getTargetMapId());
		npcObj.putInt("targetCol", npc.getTargetCol());
		npcObj.putInt("targetRow", npc.getTargetRow());
		npcObj.putInt("face", npc.getFace());
		npcObj.putInt("moveType", npc.getMoveType());
		npcObj.putInt("speedLevel", npc.getSpeedLevel());
		npcObj.putBool("penetrable", npc.isPenetrable());

		return npcObj;
	}

}
