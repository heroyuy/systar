package com.soyomaker.message.handlers.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.handlers.utils.PlayerUtil;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.PlayerService;

@Component("listPlayerHandler")
public class ListPlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PlayerUtil playerUtil;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		List<Player> list = playerService.findByUserId(session.getUser()
				.getId());
		GameObject msgSent = this.buildPackage(msg);
		msgSent.putObjectArray("playerList", this.convertPlayerList(list));
		netTransceiver.sendMessage(session, msgSent);
	}

	private List<GameObject> convertPlayerList(List<Player> list) {
		List<GameObject> objList = new ArrayList<GameObject>();
		if (list != null) {
			for (Player player : list) {
				objList.add(playerUtil.getPlayerInfo(player));
			}
		}
		return objList;
	}
}
