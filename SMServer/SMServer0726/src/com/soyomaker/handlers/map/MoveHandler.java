package com.soyomaker.handlers.map;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;
import com.soyomaker.service.PlayerService;

@Component("moveHandler")
public class MoveHandler extends AbHandler {

	private Logger log = Logger.getLogger(MoveHandler.class);

	@Autowired
	private DictManager dictManager;

	@Autowired
	private PlayerService playerService;

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
			
			//延迟写
			delayUpdate(player, x, y);
			this.sendMessage(session, msg.getType(), false, "行走验证成功");
		} else {
			// 验证失败
			this.sendMessage(session, msg.getType(), false, "行走验证失败");
		}
	}

	private void delayUpdate(Player player, int x, int y) {
		if (player.getLastUpdateTime() == null) {
			// 第一次更新x,y
			player.setLastUpdateTime(new Date());
			playerService.updateXY(player.getId(), x, y);
			return;
		}
		
		if (intervalTime(new Date(), player.getLastUpdateTime()) >= 60) {
			// 大于或等于60秒更新一次
			player.setLastUpdateTime(new Date());
			playerService.updateXY(player.getId(), x, y);
			return;
		}
	}

	private int intervalTime(Date now, Date lastTime) {
		long nowL = now.getTime();
		long lastL = lastTime.getTime();
		long ei = nowL - lastL;

		// TODO 需要测试
		return (int) (ei / (1000));
	}
}
