package com.soyomaker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.service.MapDataService;
import com.soyomaker.service.PlayerService;

public class DictManager {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private MapDataService mapDataService;

	private Player player;

	private Map<Integer, MapData> mapDataMap;

	public Player getPlayer() {
		return player;
	}

	public MapData getMapData(int mapId) {
		return mapDataMap.get(mapId);
	}

	public void load() {
		// 加载player配置
		player = playerService.findById(-1);
		// 加载mapData
		mapDataMap = new HashMap<Integer, MapData>();
		List<MapData> list = mapDataService.getAll();
		for (MapData mapData : list) {
			mapData.updateWayMatrix();
			mapDataMap.put(mapData.getId(), mapData);
		}
	}

	public void unload() {

	}

}
