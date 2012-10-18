package com.soyomaker.message.handlers.map;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("moveHandler")
public class MoveHandler extends AbHandler {

	private Logger log = Logger.getLogger(MoveHandler.class);

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// 角色行走，只发行走序列
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		// 验证行走序列
		int x = player.getX();
		int y = player.getY();
		boolean res = true;
		// 1s发送一次,客户端的行走发送的数据
		Collection<Integer> steps = msg.getIntArray("steps");
		for (int step : steps) {
			switch (step) {
			case Player.UP: {
				y--;
			}
				break;
			case Player.DOWN: {
				y++;
			}
				break;
			case Player.LEFT: {
				x--;
			}
				break;
			case Player.RIGHT: {
				x++;
			}
				break;

			default: {
				log.debug("error step\"" + step + "\"");
			}
				break;
			}
			int way = mapData.getWay(x, y);
			if (way == MapData.IMPASSABLE) {
				res = false;
				break;
			}
		}
		if (res) {
			// 验证通过
			player.setX(x);
			player.setY(y);
			this.sendMessage(session, msg, false, "行走验证成功");
		} else {
			// 验证失败
			this.sendMessage(session, msg, false, "行走验证失败");
		}
	}

}
