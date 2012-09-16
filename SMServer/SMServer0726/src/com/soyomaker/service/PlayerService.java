package com.soyomaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;

/**
 * @author chenwentao
 * 
 */
@Service("playerService")
@Transactional
public class PlayerService extends AbstractService<Player> {

	@Autowired
	private DictManager dictManager;

	public List<Player> findByUserId(long userId) {
		return find("from Player p where p.userId=?", userId);
	}

	public Player findById(int playerId) {
		return findUnique("from Player p where p.id=?", playerId);
	}

	public Player findByName(String name) {
		return findUnique("from Player p where p.name=? ", name);
	}

	public Player newPlayer(long userId, String name) {
		Player player = new Player();
		player.setUserId(userId);
		player.setName(name);
		// 初始化
		Player playerDict = dictManager.getPlayer();
		player.setMapId(playerDict.getMapId());
		player.setMapName(playerDict.getMapName());
		player.setX(playerDict.getX());
		player.setY(playerDict.getY());
		player.setAvatar(playerDict.getAvatar());
		player.setLevel(playerDict.getLevel());
		player.setExp(playerDict.getExp());
		player.setMoney(playerDict.getMoney());
		player.setHp(playerDict.getHp());
		player.setSp(playerDict.getSp());
		player.setStre(playerDict.getStre());
		player.setAgil(playerDict.getAgil());
		player.setInte(playerDict.getInte());
		player.setVita(playerDict.getVita());
		boolean status = this.save(player);
		if (status) {
			return this.findByName(name);
		} else {
			return null;
		}
	}

}
