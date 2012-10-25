package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("applyTaskHandler")
public class ApplyTaskHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int id = msg.getInt("taskId");
		// (1)检查是否存在指定id的任务
		Task task = dictManager.getTask(id);
		if (task == null) {
			this.sendMessage(session, msg, false, "任务不存在");
			return;
		}
		// (2)是否已经申请(正在进行或者已经完成)
		Player player = session.getUser().getPlayer();
		PlayerTask playerTask = player.getPlayerTask(id);
		if (playerTask != null) {
			this.sendMessage(session, msg, false, "任务正在进行或者已经完成");
			return;
		}
		//TODO 检查申请条件
		// (3)注册任务
		PlayerTask pt = new PlayerTask(player.getId(), id);
		player.setPlayerTask(pt.getId().getTaskId(), pt);
		this.sendMessage(session, msg, true, "任务申请成功");
	}

}
