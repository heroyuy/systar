package com.soyomaker.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.soyomaker.service.PlayerService;

public class DictManager {

	@Autowired
	private PlayerService playerService;

	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void load() {
		// 加载player配置
		player=playerService.findPlayerById(-1);
	}

	public void unload() {

	}

}
