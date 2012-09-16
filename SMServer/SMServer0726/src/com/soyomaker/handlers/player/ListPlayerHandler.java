package com.soyomaker.handlers.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.handlers.utils.PlayerUtil;
import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.PlayerService;

@Component("listPlayerHandler")
public class ListPlayerHandler extends AbHandler {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private NetTransceiver netTransceiver;

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
				objList.add(PlayerUtil.getPlayerInfo(player));
			}
		}
		return objList;
	}
}
