package com.soyomaker.message.handlers.task;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("listTaskHandler")
public class ListTaskHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		Collection<GameObject> taskObjs = new ArrayList<GameObject>();
		for (PlayerTask pt : session.getUser().getPlayer().getPlayerTaskList()) {
			if (!pt.isFinished()) {
				GameObject taskObj = new GameObject();
				Task task = dictManager.getTask(pt.getTaskId());
				taskObj.putInt("id", task.getId());
				taskObj.putString("name", task.getName());
				taskObj.putString("desc", task.getIntro());
				taskObj.putInt("type", task.getType());
				taskObj.putInt("step", pt.getStep());
				taskObj.putBool("finished", pt.isFinished());
				taskObjs.add(taskObj);
			}
		}
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("taskList", taskObjs);
		netTransceiver.sendMessage(session, msgSent);
	}

}
