package com.soyomaker.message.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;

@Component("playerUtil")
public class PlayerUtil {

	@Autowired
	private DictManager dictManager;

	public GameObject getPlayerInfo(Player player) {
		GameObject playerObj = new GameObject();
		playerObj.putInt("playerId", player.getId());
		playerObj.putString("name", player.getName());
		playerObj.putInt("mapId", player.getMapId());
		playerObj.putString("mapName", player.getMapName());
		playerObj.putInt("col", player.getCol());
		playerObj.putInt("row", player.getRow());
		playerObj.putInt("avatar", player.getAvatar());
		playerObj.putInt("level", player.getLevel());
		playerObj.putInt("exp", player.getExp());
		playerObj.putInt("lastExp", dictManager.getExp(player.getLevel()));
		playerObj.putInt("nextExp", dictManager.getExp(player.getLevel() + 1));
		playerObj.putInt("money", player.getMoney());
		playerObj.putInt("hp", player.getHp());
		playerObj.putInt("sp", player.getSp());
		playerObj.putInt("maxHP", player.getMaxHP());
		playerObj.putInt("maxSP", player.getMaxSP());
		playerObj.putInt("stre", player.getStre());
		playerObj.putInt("agil", player.getAgil());
		playerObj.putInt("inte", player.getInte());
		playerObj.putInt("vita", player.getVita());
		playerObj.putInt("atk", player.getAtk());
		playerObj.putInt("def", player.getDef());
		playerObj.putInt("hit", player.getHit());
		playerObj.putInt("flee", player.getFlee());
		return playerObj;
	}

}
