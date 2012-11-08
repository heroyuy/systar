package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.message.util.TaskUtil;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.TaskStep;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("nextStepHandler")
public class NextStepHandler extends AbHandler {

	@Autowired
	private MessagePusher messagePusher;

	@Autowired
	private TaskUtil taskUtil;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		int id = msg.getInt("taskId");
		GameObject msgSent = this.buildPackage(msg);
		Player player = session.getUser().getPlayer();
		PlayerTask pt = player.getPlayerTask(id);
		// (1)检查任务是否存在
		if (pt == null) {
			this.addOperateResultToPackage(msgSent, false, "没有此任务");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (2)检查任务是否已经完成
		if (pt.isFinished()) {
			this.addOperateResultToPackage(msgSent, false, "此任务已经完成");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (3)检查任务步骤是否已经结束
		if (pt.isStepOver()) {
			this.addOperateResultToPackage(msgSent, false, "此任务步骤已经结束");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		TaskStep curTaskStep = pt.getCurTaskStep();
		int step = pt.getStep() + 1;
		// (4)检查任务步骤是否结束
		if (step == pt.getTask().getSteps().size()) {
			pt.setStepOver(true);
		}
		pt.setStep(step);
		// (5)反馈消息
		this.addOperateResultToPackage(msgSent, true, "步骤完成");
		msgSent.putBool("stepOver", pt.isStepOver());
		netTransceiver.sendMessage(session, msgSent);
		// (6)触发更新NPC状态
		messagePusher.updateNpcState(session);
		// (7)执行任务申请的服务器命令
		taskUtil.executeTaskCommandList(player,
				curTaskStep.getTaskCommandList());
	}

}
