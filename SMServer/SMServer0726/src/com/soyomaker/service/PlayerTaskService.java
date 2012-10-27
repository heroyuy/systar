package com.soyomaker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;

/**
 * 
 * @author wp_g4
 * 
 */
@Service("playerTaskService")
@Transactional
public class PlayerTaskService extends AbstractService<PlayerTask> {

	@Autowired
	private DictManager dictManager;

	/**
	 * 查找玩家任务列表
	 * 
	 * @param playerId
	 *            玩家ID
	 * @return 玩家的任务列表
	 */
	public List<PlayerTask> findByPlayerId(int playerId) {
		List<PlayerTask> ptList = find(
				"from PlayerTask pt where pt.id.playerId=?", playerId);
		for (PlayerTask pt : ptList) {
			pt.setTask(dictManager.getTask(pt.getTaskId()));
		}
		return ptList;
	}

	/**
	 * 获取玩家可接收任务列表
	 * 
	 * @param player
	 *            玩家
	 * @return 可接收任务列表
	 */
	public List<Task> getAvailableTaskList(Player player) {
		List<Task> taskList = new ArrayList<Task>();
		for (Task task : dictManager.getTaskList()) {
			if (player.canApplyTask(task)) {
				taskList.add(task);
			}
		}
		return taskList;
	}

	/**
	 * 获取玩家已完成任务列表
	 * 
	 * @param player
	 *            玩家
	 * @return 已完成任务列表
	 */
	public List<PlayerTask> getFinishedTaskList(Player player) {
		List<PlayerTask> ptList = new ArrayList<PlayerTask>();
		for (PlayerTask playerTask : player.getPlayerTaskList()) {
			if (playerTask.isFinished()) {
				ptList.add(playerTask);
			}
		}
		return ptList;
	}

	/**
	 * 获取玩家未完成任务列表
	 * 
	 * @param player
	 *            玩家
	 * @return 未完成任务列表
	 */
	public List<PlayerTask> getUnfinishedTaskList(Player player) {
		List<PlayerTask> ptList = new ArrayList<PlayerTask>();
		for (PlayerTask playerTask : player.getPlayerTaskList()) {
			if (!playerTask.isFinished()) {
				ptList.add(playerTask);
			}
		}
		return ptList;
	}

	/**
	 * 初始化玩家任务列表
	 * 
	 * @param player
	 *            玩家
	 */
	public void initPlayerTaskList(Player player) {
		// 清空
		player.clearPlayerTaskList();
		// 加载已有任务
		List<PlayerTask> ptList = findByPlayerId(player.getId());
		for (PlayerTask playerTask : ptList) {
			player.addPlayerTask(playerTask);
		}
		// 扫描自动接收的任务
		this.scanAutoApplyTask(player);
	}

	public void scanAutoApplyTask(Player player) {
		List<Task> availableTaskList = this.getAvailableTaskList(player);
		for (Task task : availableTaskList) {
			if (task.getType() == Task.TASK_TYPE_PLOTLINE) {
				// 主线任务自动接收
				PlayerTask pt = new PlayerTask(player, task);
				player.addPlayerTask(pt);
			}
		}
	}

	/**
	 * 同步玩家的任务列表到数据库
	 * 
	 * @param player
	 */
	public void updatePlayerTaskList(Player player) {
		if (player != null) {
			this.delete("delete from PlayerTask pt where pt.id.playerId=?",
					player.getId());
			for (PlayerTask pt : player.getPlayerTaskList()) {
				this.saveOrUpdate(pt);
			}
		}
	}

}
