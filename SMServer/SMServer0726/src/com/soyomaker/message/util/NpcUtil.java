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
		npcObj.putInt("x", npc.getX());
		npcObj.putInt("y", npc.getY());
		npcObj.putInt("targetMapId", npc.getTargetMapId());
		npcObj.putInt("targetX", npc.getTargetX());
		npcObj.putInt("targetY", npc.getTargetY());
		npcObj.putInt("face", npc.getFace());
		npcObj.putInt("moveType", npc.getMoveType());
		npcObj.putInt("speedLevel", npc.getSpeedLevel());
		npcObj.putBool("penetrable", npc.isPenetrable());

		return npcObj;
	}

}
