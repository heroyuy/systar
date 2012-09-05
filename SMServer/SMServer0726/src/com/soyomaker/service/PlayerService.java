package com.soyomaker.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Player;

/**
 * @author chenwentao
 * 
 */
@Service("playerService")
@Transactional
public class PlayerService extends AbstractService<Player> {

	/**
	 * 更新player的x,y
	 * 
	 * @param id
	 * @param x
	 * @param y
	 */
	public void updateXY(Serializable id, int x, int y) {
		Player player = this.get(id);
		player.setX(x);
		player.setY(y);
		this.update(player);
	}

	public List<Player> findPlayerByUserId(long userId) {
		return find("from Player p where p.userId=?", userId);
	}

	public Player findPlayerById(int playerId) {
		return findUnique("from Player p where p.id=?", playerId);
	}

	public Player findPlayerByName(String name) {
		return findUnique("from Player p where p.name=? ", name);
	}

}
