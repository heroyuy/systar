package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.message.util.TaskUtil;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("applyTaskHandler")
public class ApplyTaskHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Autowired
	private MessagePusher messagePusher;

	@Autowired
	private TaskUtil taskUtil;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		int id = msg.getInt("taskId");
		GameObject msgSent = this.buildPackage(msg);
		// (1)检查是否存在指定id的任务
		Task task = dictManager.getTask(id);
		if (task == null) {
			this.addOperateResultToPackage(msgSent, false, "任务不存在");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (2)是否已经申请(正在进行或者已经完成)
		Player player = session.getUser().getPlayer();
		PlayerTask playerTask = player.getPlayerTask(id);
		if (playerTask != null) {
			this.addOperateResultToPackage(msgSent, false, "任务正在进行或者已经完成");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// TODO 检查申请条件
		// (3)注册任务
		PlayerTask pt = new PlayerTask(player.getId(), task);
		player.addPlayerTask(pt);
		this.addOperateResultToPackage(msgSent, true, "任务申请成功");
		netTransceiver.sendMessage(session, msgSent);
		// (4)触发更新NPC状态
		messagePusher.updateNpcState(session);
		// (5)执行任务申请的服务器命令
		taskUtil.executeTaskCommandList(player, task.getApplyTaskCommandList());
	}

}
