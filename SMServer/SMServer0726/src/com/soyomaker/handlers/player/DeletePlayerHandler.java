package com.soyomaker.handlers.player;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.model.dao.DaoManager;
import com.soyomaker.model.dao.IDao;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

public class DeletePlayerHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int playerId = msg.getInt("playerId");
		// (1)检查是否有此角色
		IDao<Player> playerDao = DaoManager.getInatance().getDao(Player.class);
		Player player = playerDao.get(playerId);
		if (player == null) {
			this.sendMessage(session, msg.getType(), false, "角色不存在");
		}
		// (2)检查角色是否属于user
		if (player.getUserId() != session.getUserId()) {
			this.sendMessage(session, msg.getType(), false, "这个角色不属于你");
		}
		// (3)删除
		boolean status = playerDao.delete(playerId);
		this.sendMessage(session, msg.getType(), status, status ? "删除成功"
				: "删除失败");
	}
}
