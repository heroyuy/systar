package com.soyomaker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.service.MapDataService;
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

	private Player player;// 角色初始数据

	private Map<Integer, MapData> mapDataMap;// 地图数据

	private Map<Integer, Task> taskMap;

	public Player getPlayer() {
		return player;
	}

	public MapData getMapData(int mapId) {
		return mapDataMap.get(mapId);
	}

	public Task getTask(int taskId) {
		return taskMap.get(taskId);
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
	}

	public void unload() {

	}

}
