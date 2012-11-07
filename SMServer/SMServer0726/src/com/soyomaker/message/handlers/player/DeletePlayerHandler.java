package com.soyomaker.message.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.PlayerService;

@Component("deletePlayerHandler")
public class DeletePlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		int playerId = msg.getInt("playerId");
		// (1)检查是否有此角色
		Player player = playerService.get(playerId);
		if (player == null) {
			this.sendResponseMessage(session, msg, false, "角色不存在");
		}
		// (2)检查角色是否属于user
		if (!player.getUserId().equals(session.getUser().getId())) {
			this.sendResponseMessage(session, msg, false, "这个角色不属于你");
		}
		// (3)删除
		boolean status = playerService.delete(playerId);
		this.sendResponseMessage(session, msg, status, status ? "删除成功"
				: "删除失败");
	}
}
