package com.soyomaker.message.handlers.map;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.MapEntry;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("loadMapHandler")
public class LoadMapHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		GameObject msgSent = this.buildPackage(msg);
		Collection<GameObject> mapEntryObjList = new ArrayList<GameObject>();
		Collection<MapEntry> mapEntryList = mapData.getMapEntryMap().values();
		if (mapEntryList != null) {
			for (MapEntry mapEntry : mapEntryList) {
				GameObject mapEntryObj = new GameObject();
				mapEntryObj.putInt("id", mapEntry.getId());
				mapEntryObj.putInt("mapId", mapEntry.getMapId());
				mapEntryObj.putInt("x", mapEntry.getX());
				mapEntryObj.putInt("y", mapEntry.getY());
				mapEntryObj.putInt("avatar", mapEntry.getAvatar());
				mapEntryObjList.add(mapEntryObj);
			}
		}
		msgSent.putObjectArray("mapEntryList", mapEntryObjList);
		netTransceiver.sendMessage(session, msgSent);
	}

}
