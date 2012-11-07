package com.soyomaker.message.handlers.task;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.util.TaskUtil;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;

@Component("listTaskHandler")
public class ListTaskHandler extends AbHandler {

	@Autowired
	private TaskUtil taskUtil;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		Player player = session.getUser().getPlayer();
		Collection<GameObject> taskObjs = new ArrayList<GameObject>();
		for (PlayerTask pt : player.getUnfinishedTaskList()) {
			taskObjs.add(taskUtil.convertTask(player, pt.getTask()));
		}
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("taskList", taskObjs);
		netTransceiver.sendMessage(session, msgSent);
	}

}
