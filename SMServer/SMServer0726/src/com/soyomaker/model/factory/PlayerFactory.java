/**
 * 
 */
package com.soyomaker.model.factory;

import com.soyomaker.model.Player;

/**
 * Player Factory
 * 
 * @author chenwentao
 * 
 */
public class PlayerFactory {

	private PlayerFactory() {
	}

	public static Player createPlayer(String name, Long userId) {
		Player player = new Player();
		player.setName(name);
		player.setUserId(userId);
		// TODO 此处应该读配置
		player.setMapId(108000012);
		player.setX(3);
		player.setY(8);
		player.setAvatar(1);
		player.setLevel(1);
		player.setExp(0);
		player.setMoney(300);
		player.setHp(100);
		player.setSp(100);
		player.setStre(9);
		player.setAgil(9);
		player.setInte(9);
		player.setVita(9);
		player.setDext(9);
		player.setLuck(9);

		return player;
	}

}
