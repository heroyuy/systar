package com.soyomaker.handlers.utils;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;

public class PlayerUtil {

	public static GameObject getPlayerInfo(Player player) {
		GameObject playerObj = new GameObject();
		playerObj.putInt("playerId", player.getId());
		playerObj.putString("name", player.getName());
		playerObj.putInt("mapId", player.getMapId());
		playerObj.putString("mapName", player.getMapName());
		playerObj.putInt("x", player.getX());
		playerObj.putInt("y", player.getY());
		playerObj.putInt("avatar", player.getAvatar());
		playerObj.putInt("level", player.getLevel());
		playerObj.putInt("exp", player.getExp());
		playerObj.putInt("money", player.getMoney());
		playerObj.putInt("hp", player.getHp());
		playerObj.putInt("sp", player.getSp());
		playerObj.putInt("stre", player.getStre());
		playerObj.putInt("agil", player.getAgil());
		playerObj.putInt("inte", player.getInte());
		playerObj.putInt("vita", player.getVita());
		return playerObj;
	}

}
