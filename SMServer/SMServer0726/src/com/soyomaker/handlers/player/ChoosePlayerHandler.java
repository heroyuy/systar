package com.soyomaker.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.PlayerService;
import com.soyomaker.service.PlayerTaskService;

@Component("choosePlayerHandler")
public class ChoosePlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerTaskService playerTaskService;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO 未考虑切换角色的情况
		Integer playerId = msg.getInt("playerId");
		Player player = playerService.get(playerId);
		if (player == null) {
			this.sendMessage(session, msg.getType(), false, "选择角色不存在 ");
			return;
		}
		if (player.getUserId() != session.getUser().getId()) {
			this.sendMessage(session, msg.getType(), false, "这个角色不属于你 ");
			return;
		}
		session.getUser().setPlayer(player);
		// 初始化任务列表
		playerTaskService.choosePlayer(player);
		this.sendMessage(session, msg.getType(), true, "选择角色成功");
	}

}
