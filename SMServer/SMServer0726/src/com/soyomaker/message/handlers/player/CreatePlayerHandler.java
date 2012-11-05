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
	public void handleMessage(UserSession session, GameObject msg) {
		String name = msg.getString("name");
		// (1)检查包是否完整
		if (name == null) {
			return;
		}
		// (2)检查呢称长度
		if (name.length() < 2) {
			this.sendNormalMessage(session, msg, false, "呢称长度不能小于2");
			return;
		}
		// (3)检查呢称是否已被使用
		Player player = playerService.findByName(name);
		if (player != null) {
			this.sendNormalMessage(session, msg, false, "呢称已被使用");
			return;
		}
		// (4)新建角色
		player = playerService.newPlayer(session.getUser().getId(), name);
		if (player != null) {
			GameObject msgSent = this.buildNormalPackage(msg, true, "创建成功");
			GameObject playerObject = playerUtil.getPlayerInfo(player);
			msgSent.putObject("player", playerObject);
			netTransceiver.sendMessage(session, msgSent);
		} else {
			this.sendNormalMessage(session, msg, false, "创建失败");
		}

	}

}
