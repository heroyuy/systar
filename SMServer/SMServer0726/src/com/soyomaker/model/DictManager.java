package com.soyomaker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.service.LevelExpService;
import com.soyomaker.service.MapDataService;
import com.soyomaker.service.MapEntryService;
import com.soyomaker.service.PlayerService;
import com.soyomaker.service.TaskService;
import com.soyomaker.service.TaskStepService;

public class DictManager {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private MapDataService mapDataService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskStepService taskStepService;

	@Autowired
	private LevelExpService levelExpService;

	@Autowired
	private MapEntryService mapEntryService;

	private Player player;// 角色初始数据

	private Map<Integer, MapData> mapDataMap;// 地图数据

	private Map<Integer, Task> taskMap;

	private Map<Integer, Integer> levelExpMap;

	public Player getPlayer() {
		return player;
	}

	public MapData getMapData(int mapId) {
		return mapDataMap.get(mapId);
	}

	public Task getTask(int taskId) {
		return taskMap.get(taskId);
	}

	public int getExp(int level) {
		return levelExpMap.get(level);
	}

	public void load() {
		// 加载player配置
		player = playerService.findById(-1);
		// 加载mapData
		mapDataMap = new HashMap<Integer, MapData>();
		List<MapData> list = mapDataService.findAll();
		for (MapData mapData : list) {
			mapData.updateWayMatrix();
			mapDataMap.put(mapData.getId(), mapData);
		}
		// 加载task
		taskMap = new HashMap<Integer, Task>();
		List<Task> taskList = taskService.findAll();
		for (Task task : taskList) {
			task.setSteps(taskStepService.findByTaskId(task.getId()));
			taskMap.put(task.getId(), task);
		}
		// 加载等级经验配置
		levelExpMap = levelExpService.getLevelExpMap();
		// 加载传送点
		for (MapData mapData : list) {
			List<MapEntry> mapEntryList = mapEntryService.findByMapId(mapData
					.getId());
			Map<Integer, MapEntry> mapEntryMap = new HashMap<Integer, MapEntry>();
			for (MapEntry mapEntry : mapEntryList) {
				mapEntryMap.put(mapEntry.getId(), mapEntry);
			}
			mapData.setMapEntryMap(mapEntryMap);
		}
	}

	public void unload() {

	}

}
