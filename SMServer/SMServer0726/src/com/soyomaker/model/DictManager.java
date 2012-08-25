package com.soyomaker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.service.MapDataService;
import com.soyomaker.service.PlayerService;

public class DictManager {

	private static final Logger log = Logger.getLogger(DictManager.class);

	@Autowired
	private PlayerService playerService;

	@Autowired
	private MapDataService mapDataService;

	private Player player;

	private Map<Integer, MapData> mapDataMap;

	public Player getPlayer() {
		return player;
	}

	public void load() {
		// 加载player配置
		player = playerService.findPlayerById(-1);
		// 加载mapData
		mapDataMap = new HashMap<Integer, MapData>();
		List<MapData> list = mapDataService.getAllMapData();
		for (MapData mapData : list) {
			mapDataMap.put(mapData.getId(), mapData);
		}
	}

	public void unload() {

	}

}
