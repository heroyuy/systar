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
import com.soyomaker.net.session.UserSession;

@Component("moveHandler")
public class MoveHandler extends AbHandler {

	private Logger log = Logger.getLogger(MoveHandler.class);

	@Autowired
	private DictManager dictManager;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		// 角色行走，只发行走序列
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		// 验证行走序列
		int col = player.getCol();
		int row = player.getRow();
		Collection<Integer> steps = msg.getIntArray("steps");
		boolean res = true;
		GameObject msgSent = this.buildPackage(msg);
		for (int step : steps) {
			switch (step) {
			case Player.UP: {
				row--;
			}
				break;
			case Player.DOWN: {
				row++;
			}
				break;
			case Player.LEFT: {
				col--;
			}
				break;
			case Player.RIGHT: {
				col++;
			}
				break;

			default: {
				log.debug("error step\"" + step + "\"");
			}
				break;
			}
			int way = mapData.getWay(col, row);
			if (way == MapData.IMPASSABLE) {
				res = false;
				break;
			}
		}
		if (res) {
			// 验证通过
			player.setCol(col);
			player.setRow(row);
			this.addOperateResultToPackage(msgSent, true, "行走验证成功");
		} else {
			// 验证失败
			this.addOperateResultToPackage(msgSent, false, "行走验证失败");
		}
		netTransceiver.sendMessage(session, msgSent);
	}

}
