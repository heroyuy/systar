package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Player;
import com.soyomaker.model.PlayerTask;

/**
 * 
 * @author wp_g4
 * 
 */
@Service("playerTaskService")
@Transactional
public class PlayerTaskService extends AbstractService<PlayerTask> {

	public List<PlayerTask> findByPlayerId(int playerId) {
		return find("from PlayerTask pt where pt.id.playerId=?", playerId);
	}

	/**
	 * 用户选择角色
	 * 
	 * @param player
	 */
	public void choosePlayer(Player player) {
		// 保证清空上次的
		player.clearNowTasks();
		List<PlayerTask> pts = findByPlayerId(player.getId());
		for (PlayerTask playerTask : pts) {
			player.setPlayerTasks(playerTask.getId().getTaskId(), playerTask);
		}
	}

	/**
	 * 同步player的当前任务列表到数据库
	 * 
	 * @param player
	 */
	public void updatePlayerTask(Player player) {

	}

}
