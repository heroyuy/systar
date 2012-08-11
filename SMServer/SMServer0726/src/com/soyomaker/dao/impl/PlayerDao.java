package com.soyomaker.dao.impl;

import java.util.List;

import com.soyomaker.dao.BasicDao;
import com.soyomaker.model.Player;

public class PlayerDao extends BasicDao<Player> {

	public Player getPlayer(String name) {
		List<Player> list = this.get("name", "=", name);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
