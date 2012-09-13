package com.soyomaker.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.Player;
import com.soyomaker.model.PlayerTask;
import com.soyomaker.model.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("nextStepHandler")
public class NextStepHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int id = msg.getInt("id");
		Player player = session.getUser().getPlayer();
		PlayerTask pt = player.getPlayerTask(id);
		Task task = dictManager.getTask(id);
		// (1)检查任务是否存在
		if (pt == null) {
			this.sendMessage(session, msg, false, "没有此任务");
			return;
		}
		int step = pt.getStep() + 1;
		// (2)检查步骤，以确定是否是完成任务
		int stepNum = task.getSteps().size();
		if (step > stepNum) {
			this.sendMessage(session, msg, false, "此任务已经完成");
			return;
		}
		if (step == stepNum) {
			// TODO 完成任务，检查条件
			pt.setFinished(true);
		}
		pt.setStep(step);
		GameObject msgSent = this.buildPackage(msg, true,
				pt.isFinished() ? "任务完成" : "步骤完成");
		msgSent.putBool("finished", pt.isFinished());
		netTransceiver.sendMessage(session, msgSent);
	}

}
