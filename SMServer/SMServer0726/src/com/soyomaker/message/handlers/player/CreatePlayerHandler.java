package com.soyomaker.message.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.handlers.utils.PlayerUtil;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.PlayerService;

@Component("createPlayerHandler")
public class CreatePlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerUtil playerUtil;

	@Override
	public boolean checkRequestPackage(GameObject msg) {
		return msg.containsKey("name");
	}

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		String name = msg.getString("name");
		GameObject msgSent = this.buildPackage(msg);
		// (1)检查呢称长度
		if (name.length() < 2) {
			this.addOperateResultToPackage(msgSent, false, "呢称长度不能小于2");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (2)检查呢称是否已被使用
		Player player = playerService.findByName(name);
		if (player != null) {
			this.addOperateResultToPackage(msgSent, false, "呢称已被使用");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (3)新建角色
		player = playerService.newPlayer(session.getUser().getId(), name);
		if (player == null) {
			this.addOperateResultToPackage(msgSent, false, "创建失败");
			netTransceiver.sendMessage(session, msgSent);
		} else {
			this.addOperateResultToPackage(msgSent, true, "创建成功");
			GameObject playerObject = playerUtil.getPlayerInfo(player);
			msgSent.putObject("player", playerObject);
			netTransceiver.sendMessage(session, msgSent);
		}

	}

}
