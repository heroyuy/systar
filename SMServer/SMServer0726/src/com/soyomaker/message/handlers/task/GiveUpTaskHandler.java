package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.PlayerTaskService;

@Component("giveUpTaskHandler")
public class GiveUpTaskHandler extends AbHandler {

	@Autowired
	private PlayerTaskService playerTaskService;

	@Autowired
	private MessagePusher messagePusher;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		int id = msg.getInt("taskId");
		GameObject msgSent = this.buildPackage(msg);
		// (1)检查是否有此任务
		Player player = session.getUser().getPlayer();
		PlayerTask pt = player.getPlayerTask(id);
		if (pt == null) {
			this.addOperateResultToPackage(msgSent, false, "任务不存在");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (2)检查任务是否已经完成
		if (pt.isFinished()) {
			this.addOperateResultToPackage(msgSent, false, "任务已完成，不能放弃");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (3)检查任务是否是主线任务，主线任务不能放弃
		if (pt.getTask().getType() == Task.TYPE_PLOTLINE) {
			this.addOperateResultToPackage(msgSent, false, "主线任务不能放弃");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (4)移除任务
		playerTaskService.removePlayerTask(player, pt);
		this.addOperateResultToPackage(msgSent, true, "放弃任务成功");
		netTransceiver.sendMessage(session, msgSent);
		// 触发更新NPC状态
		messagePusher.updateNpcState(session);
	}

}
