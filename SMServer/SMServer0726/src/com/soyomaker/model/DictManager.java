package com.soyomaker.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.model.task.Task;
import com.soyomaker.model.task.TaskCommand;
import com.soyomaker.model.task.TaskStep;
import com.soyomaker.service.LevelExpService;
import com.soyomaker.service.MapDataService;
import com.soyomaker.service.NpcService;
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
	private NpcService npcService;

	private Player player;// 角色初始数据

	private Map<Integer, MapData> mapDataMap;// 地图数据

	private Map<Integer, Task> taskMap;// 任务表

	private Map<Integer, Integer> levelExpMap;// 等级-经验表

	/**
	 * 获取初始玩家数据
	 * 
	 * @return 初始玩家数据
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 获取地图数据
	 * 
	 * @param mapId
	 *            地图ID
	 * @return 地图数据
	 */
	public MapData getMapData(int mapId) {
		return mapDataMap.get(mapId);
	}

	/**
	 * 获取任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @return 任务
	 */
	public Task getTask(int taskId) {
		return taskMap.get(taskId);
	}

	/**
	 * 获取任务列表
	 * 
	 * @return 任务列表
	 */
	public Collection<Task> getTaskList() {
		return taskMap.values();
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
			task.setApplyTaskCommandList(TaskCommand.parseStringToCommands(task
					.getApplyCmd()));
			task.setFinishTaskCommandList(TaskCommand
					.parseStringToCommands(task.getFinishCmd()));
			List<TaskStep> taskStepList = taskStepService.findByTaskId(task
					.getId());
			for (TaskStep taskStep : taskStepList) {
				taskStep.setTaskCommandList(TaskCommand
						.parseStringToCommands(taskStep.getCommand()));
			}
			task.setSteps(taskStepList);
			taskMap.put(task.getId(), task);
		}
		// 加载等级经验配置
		levelExpMap = levelExpService.getLevelExpMap();
		// 加载Npc
		for (MapData mapData : list) {
			List<Npc> npcList = npcService.findByMapId(mapData.getId());
			Map<Integer, Npc> npcMap = new HashMap<Integer, Npc>();
			for (Npc npc : npcList) {
				npcMap.put(npc.getId(), npc);
			}
			mapData.setNpcMap(npcMap);
		}
	}

	public void unload() {

	}

}
