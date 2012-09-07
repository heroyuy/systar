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

	public List<PlayerTask> findPlayerTaskByPlayerId(int playerId) {
		return find("from PlayerTask pt where pt.playerId=?", playerId);
	}

	/**
	 * 从数据库读取player的所有任务
	 * 
	 * @param player
	 */
	public void loadPlayerTask(Player player) {
		player.setPlayerTasks(this.findPlayerTaskByPlayerId(player.getId()));
	}

	/**
	 * 同步player的当前任务列表到数据库
	 * 
	 * @param player
	 */
	public void updatePlayerTask(Player player) {

	}

}
