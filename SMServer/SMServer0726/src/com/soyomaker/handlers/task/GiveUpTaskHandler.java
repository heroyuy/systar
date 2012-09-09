package com.soyomaker.handlers.task;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.model.PlayerTask;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("giveUpTaskHandler")
public class GiveUpTaskHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int id = msg.getInt("id");
		// (1)检查是否有此任务
		Player player = session.getUser().getPlayer();
		PlayerTask pt = player.getPlayerTask(id);
		if (pt == null) {
			this.sendMessage(session, msg.getType(), false, "任务不存在");
			return;
		}
		// (2)检查任务是否已经完成
		if (pt.isFinished()) {
			this.sendMessage(session, msg.getType(), false, "任务已完成，不能放弃");
			return;
		}
		// (3)移除任务
		player.removePlayerTask(id);
		this.sendMessage(session, msg.getType(), true, "放弃任务成功");
	}

}
