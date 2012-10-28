package com.soyomaker.message.util;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;

@Component("taskUtil")
public class TaskUtil {

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

}
