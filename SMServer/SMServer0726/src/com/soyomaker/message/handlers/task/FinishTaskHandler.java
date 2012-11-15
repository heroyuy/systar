package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.message.util.TaskUtil;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

/**
 * 完成任务
 * 
 * @author wp_g4
 * 
 */
@Component("finishTaskHandler")
public class FinishTaskHandler extends AbHandler {

	@Autowired
	private MessagePusher messagePusher;

	@Autowired
	private TaskUtil taskUtil;

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
			this.addOperateResultToPackage(msgSent, false, "任务已完成，不能再次完成");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (3)检查任务步骤是否完成
		if (!pt.isStepOver()) {
			this.addOperateResultToPackage(msgSent, false, "任务步骤未完成");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// TODO 检查完成条件
		// (4)完成任务
		pt.setFinished(true);
		this.addOperateResultToPackage(msgSent, true, "任务完成成功");
		netTransceiver.sendMessage(session, msgSent);
		// (5)触发更新NPC状态
		messagePusher.updateNpcState(session);
		// (6)执行任务申请的服务器命令
		taskUtil.executeTaskCommandList(player, pt.getTask()
				.getFinishTaskCommandList());
	}

}
