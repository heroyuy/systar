package com.soyomaker.message.handlers.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("npcStatusHandler")
public class NPCStatusHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// (1)取当前地图上的所有npc
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		Map<Integer, Npc> npcMap = mapData.getNpcMap();
		Collection<GameObject> stateList = new ArrayList<GameObject>();
		for (Npc npc : npcMap.values()) {
			// (2)检查NPC的状态
			int state = this.checkNpcState(npc);
			GameObject stateObj = new GameObject();
			stateObj.putInt("npcId", npc.getId());
			stateObj.putInt("state", state);
			stateList.add(stateObj);
		}
		// (3)返回消息给客户端
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("stateList", stateList);
	}

	public int checkNpcState(Npc npc) {
		return 0;
	}

}
