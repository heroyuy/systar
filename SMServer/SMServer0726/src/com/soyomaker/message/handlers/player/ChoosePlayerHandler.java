package com.soyomaker.message.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.PlayerService;
import com.soyomaker.service.PlayerTaskService;

@Component("choosePlayerHandler")
public class ChoosePlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerTaskService playerTaskService;

	@Override
	public boolean checkRequestPackage(GameObject msg) {
		return msg.containsKey("playerId");
	}

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		// TODO 未考虑切换角色的情况
		Integer playerId = msg.getInt("playerId");
		GameObject msgSent = this.buildPackage(msg);
		Player player = playerService.get(playerId);
		if (player == null) {
			this.addOperateResultToPackage(msgSent, false, "选择角色不存在");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		if (!player.getUserId().equals(session.getUser().getId())) {
			this.addOperateResultToPackage(msgSent, false, "这个角色不属于你");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		session.getUser().setPlayer(player);
		// 初始化任务列表
		playerTaskService.initPlayerTaskList(player);
		this.addOperateResultToPackage(msgSent, true, "选择角色成功");
		netTransceiver.sendMessage(session, msgSent);
	}

}
