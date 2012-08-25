package com.soyomaker.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.UserService;

@Component("createPlayerHandler")
public class CreatePlayerHandler extends AbHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private NetTransceiver netTransceiver;
	

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		String name = msg.getString("name");
		// (1)检查包是否完整
		if (name == null) {
			return;
		}
		// (2)检查呢称长度
		if (name.length() < 3) {
			this.sendMessage(session, msg.getType(), false, "呢称长度不能小于3");
			return;
		}
		// (3)检查呢称是否已被使用
		Player player = userService.findUnique("from Player p where p.name=? ",
				name);
		if (player != null) {
			this.sendMessage(session, msg.getType(), false, "呢称已被使用");
			return;
		}
		// (4)添加用户到数据库
		player = new Player();
		player.setName(name);
		player.setUserId(session.getUser().getId());
		// 初始化
		Player playerDict=dictManager.getPlayer();
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
		player.setDext(playerDict.getDext());
		player.setLuck(playerDict.getDext());
		boolean status = userService.savePlayer(player);
		if (status) {
			GameObject msgSent = this.buildPackage(msg.getType(), status,
					"创建成功");
			this.insertPlayerIntoPackage(player, msgSent);
			netTransceiver.sendMessage(session, msgSent);
		} else {
			this.sendMessage(session, msg.getType(), status, "创建失败");
		}

	}

	private void insertPlayerIntoPackage(Player player, GameObject msg) {
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
		playerObj.putInt("dext", player.getDext());
		playerObj.putInt("luck", player.getLuck());
		msg.putObject("player", playerObj);
	}

}
