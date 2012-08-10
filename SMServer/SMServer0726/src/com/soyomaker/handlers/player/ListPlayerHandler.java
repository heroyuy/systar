package com.soyomaker.handlers.player;

import java.util.ArrayList;
import java.util.List;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.model.dao.DaoManager;
import com.soyomaker.model.dao.IDao;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;

public class ListPlayerHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		IDao<Player> playerDao = DaoManager.getInatance().getDao(Player.class);
		List<Player> list = playerDao.get("userId", "=", session.getUserId());
		GameObject msgSent = this.buildPackage(msg.getType());
		msgSent.putObjectArray("players", this.convertPlayerList(list));
		NetTransceiver.getInstance().sendMessage(session, msgSent);
	}

	private List<GameObject> convertPlayerList(List<Player> list) {
		List<GameObject> objList = new ArrayList<GameObject>();
		if (list != null) {
			for (Player player : list) {
				GameObject playerObj = new GameObject();
				playerObj.putInt("playerId", player.getId());
				playerObj.putString("name", player.getName());
				playerObj.putInt("mapId", player.getMapId());
				playerObj.putInt("x", player.getX());
				playerObj.putInt("y", player.getY());
				playerObj.putInt("avatar", player.getAvatar());
				playerObj.putInt("level", player.getLevel());
				playerObj.putInt("exp", player.getExp());
				playerObj.putInt("money", player.getMoney());
				playerObj.putInt("stre", player.getStre());
				playerObj.putInt("agil", player.getAgil());
				playerObj.putInt("inte", player.getInte());
				playerObj.putInt("vita", player.getVita());
				playerObj.putInt("dext", player.getDext());
				playerObj.putInt("luck", player.getLuck());
				objList.add(playerObj);
			}
		}
		return objList;
	}
}
