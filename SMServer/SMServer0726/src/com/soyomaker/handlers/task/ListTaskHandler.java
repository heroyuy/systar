package com.soyomaker.handlers.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.PlayerTask;
import com.soyomaker.model.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("listTaskHandler")
public class ListTaskHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		Map<Integer, PlayerTask> playerTasks = session.getUser().getPlayer()
				.getPlayerTasks();
		Collection<GameObject> taskObjs = new ArrayList<GameObject>();
		for (PlayerTask pt : playerTasks.values()) {
			if (!pt.isFinished()) {
				GameObject taskObj = new GameObject();
				Task task = dictManager.getTask(pt.getId().getTaskId());
				taskObj.putInt("id", task.getId());
				taskObj.putString("name", task.getName());
				taskObj.putString("desc", task.getDesc());
				taskObj.putInt("type", task.getType());
				taskObj.putInt("step", pt.getStep());
				taskObj.putBool("finished", pt.isFinished());
				taskObjs.add(taskObj);
			}
		}
		GameObject msgSent = new GameObject();
		msgSent.setType(msg.getType());
		msgSent.putObjectArray("taskList", taskObjs);
		netTransceiver.sendMessage(session, msgSent);
	}

}
