package com.soyomaker.handlers.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
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
		Player player = userService.findUnique("from Player p where p.name=? ", name);
		if (player != null) {
			this.sendMessage(session, msg.getType(), false, "呢称已被使用");
			return;
		}
		// (4)添加用户到数据库
		player = new Player();
		player.setName(name);
		player.setUserId(session.getUserId());
		// TODO 此处应该读配置
		player.setMapId(12);
		player.setX(3);
		player.setY(8);
		player.setAvatar(1);
		player.setLevel(1);
		player.setExp(0);
		player.setMoney(300);
		player.setStre(9);
		player.setAgil(9);
		player.setInte(9);
		player.setVita(9);
		player.setDext(9);
		player.setLuck(9);
		boolean status = userService.savePlayer(player);
		if (status) {
			GameObject msgSent = this.buildPackage(msg.getType(), status, "创建成功");
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
		playerObj.putInt("x", player.getX());
		playerObj.putInt("y", player.getY());
		playerObj.putInt("avatar", player.getAvatar());
		playerObj.putInt("level", player.getLevel());
		playerObj.putInt("exp", player.getExp());
		playerObj.putInt("money", player.getMoney());
		playerObj.putInt("stre", player.getStre());
		playerObj.putInt("agil", player.getAgil());
		playerObj.putInt("inte", player.getInte());
		playerObj.putInt("vita", player.getVita());
		playerObj.putInt("dext", player.getDext());
		playerObj.putInt("luck", player.getLuck());
		msg.putObject("player", playerObj);
	}

}
