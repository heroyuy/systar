package com.soyomaker.handlers.task;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.PlayerTask;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("applyTaskHandler")
public class ApplyTaskHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO
		int id = msg.getInt("id");
		// (1)检查是否存在指定id的任务
		// (2)是否已经申请(正在进行或者已经完成)
		PlayerTask playerTask = session.getUser().getPlayer().getPlayerTaskId(id);
		if (playerTask != null) {
			this.sendMessage(session, msg.getType(), false, "任务正在进行或者已经完成");
			return;
		}
		// (3)注册任务

	}

}
