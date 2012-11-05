package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("nextStepHandler")
public class NextStepHandler extends AbHandler {

	@Autowired
	private MessagePusher messagePusher;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int id = msg.getInt("taskId");
		PlayerTask pt = session.getUser().getPlayer().getPlayerTask(id);
		// (1)检查任务是否存在
		if (pt == null) {
			this.sendMessage(session, msg, false, "没有此任务");
			return;
		}
		// (2)检查任务是否已经完成
		if (pt.isFinished()) {
			this.sendMessage(session, msg, false, "此任务已经完成");
			return;
		}
		// (3)检查任务步骤是否已经结束
		if (pt.isStepOver()) {
			this.sendMessage(session, msg, false, "此任务步骤已经结束");
			return;
		}
		int step = pt.getStep() + 1;
		// (4)检查任务步骤是否结束
		if (step == pt.getTask().getSteps().size()) {
			pt.setStepOver(true);
		}
		pt.setStep(step);
		// (5)反馈消息
		GameObject msgSent = this.buildPackage(msg, true, "步骤完成");
		msgSent.putBool("stepOver", pt.isStepOver());
		netTransceiver.sendMessage(session, msgSent);
		// 触发更新NPC状态
		messagePusher.updateNPCStatus(session);
	}

}
