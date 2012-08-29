package com.soyomaker.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Player;

@Service("playerService")
@Transactional
public class PlayerService extends AbstractService {

	private static final Logger log = Logger.getLogger(PlayerService.class);

	public List<Player> findPlayerByUserId(long userId) {
		return find("from Player p where p.userId=?", userId);
	}

	public Player findPlayerById(int playerId) {
		return findUnique("from Player p where p.id=?", playerId);
	}

	public boolean deletePlay(Class<Player> class1, int playerId) {
		try {
			this.delete(class1, playerId);
			return true;
		} catch (Exception e) {
			log.error("删除角色错误", e);
			return false;
		}
	}

	/**
	 * 更新player的x,y
	 * 
	 * @param id
	 * @param x
	 * @param y
	 */
	public void updateXY(Serializable id, int x, int y) {
		Player player = this.get(Player.class, id);
		player.setX(x);
		player.setY(y);
		this.update(player);
	}

}
