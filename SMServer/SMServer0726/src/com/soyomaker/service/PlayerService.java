package com.soyomaker.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Player;

@Service("playerService")
@Transactional
public class PlayerService extends AbstractService {

	private static final Logger log = Logger.getLogger(PlayerService.class);

	private Player defaultPlayer = null;// 角色配置信息 id是-1

	public Player defaultPlayer() {
		if (defaultPlayer == null) {
			defaultPlayer = this.findPlayerById(-1);
		}
		return defaultPlayer;
	}

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

}
