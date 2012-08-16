package com.soyomaker.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.PlayerService;

public class ChoosePlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		Integer playerId = msg.getInt("playerId");
		Player player = playerService.get(Player.class, playerId);
		if (player == null) {
			this.sendMessage(session, msg.getType(), false, "选择角色不存在 ");
			return;
		}

		if (player.getUserId() != session.getUser().getId()) {
			this.sendMessage(session, msg.getType(), false, "这个角色不属于你 ");
			return;
		}
		session.getUser().setPlayer(player);
		this.sendMessage(session, msg.getType(), true, "选择角色成功");
	}

}
