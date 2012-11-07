package com.soyomaker.message.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.model.task.TaskCommand;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;

@Component("taskUtil")
public class TaskUtil {

	@Autowired
	private MessagePusher messagePusher;

	@Autowired
	private UserSessionManager userSessionManager;

	public GameObject convertTask(Player player, Task task) {
		GameObject taskObj = new GameObject();
		taskObj.putInt("id", task.getId());
		taskObj.putString("name", task.getName());
		taskObj.putInt("type", task.getType());
		taskObj.putString("intro", task.getIntro());
		taskObj.putBool("has", player.hasPlayerTask(task.getId()));
		if (player.hasPlayerTask(task.getId())) {
			// 任务进行中
			PlayerTask pt = player.getPlayerTask(task.getId());
			taskObj.putInt("step", pt.getStep());
			taskObj.putBool("stepOver", pt.isStepOver());
			taskObj.putBool("finished", pt.isFinished());
		}
		return taskObj;
	}

	public void executeTaskCommandList(Player player,
			List<TaskCommand> taskCommandList) {
		for (TaskCommand taskCommand : taskCommandList) {
			this.executeTaskCommand(player, taskCommand);
		}
	}

	public void executeTaskCommand(Player player, TaskCommand taskCommand) {
		if (taskCommand == null) {
			return;
		}
		switch (taskCommand.getType()) {
		case TaskCommand.TYPE_SWITCH_MAP: {
			this.switchMap(player, taskCommand.getParamList());
		}
			break;
		case TaskCommand.TYPE_OPERATE_NPC: {
			this.operateNpc(player, taskCommand.getParamList());
		}
			break;

		default:
			break;
		}
	}

	private void switchMap(Player player, List<String> paramList) {
		int mapId = Integer.parseInt(paramList.get(0));
		int x = Integer.parseInt(paramList.get(1));
		int y = Integer.parseInt(paramList.get(2));
		messagePusher.switchMap(
				userSessionManager.getUserSession(player.getUserId()), mapId,
				x, y);
	}

	private void operateNpc(Player player, List<String> paramList) {

	}

}
