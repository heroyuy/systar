package com.soyomaker.message.handlers.npc;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.util.TaskUtil;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("npcDialogueHandler")
public class NPCDialogueHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Autowired
	private TaskUtil taskUtil;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int npcId = msg.getInt("npcId");
		MapData mapData = dictManager.getMapData(session.getUser().getPlayer()
				.getMapId());
		// (1)检查NPC是否存在
		if (mapData.hasNpc(npcId)) {
			this.sendMessage(session, msg, false, "当前地图上不存在此NPC");
			return;
		}
		// (2)检查NPC是否可以对话
		Npc npc = mapData.getNpc(npcId);
		if (npc.getType() != Npc.TYPE_NORMAL) {
			this.sendMessage(session, msg, false, "此NPC不能对话");
			return;
		}
		// (3)进行中的任务
		Player player = session.getUser().getPlayer();
		Collection<GameObject> taskObjs = new ArrayList<GameObject>();
		for (PlayerTask pt : player.getUnfinishedTaskList()) {
			if (pt.getCurTaskStep().getNpcId() == npc.getId()) {
				taskObjs.add(taskUtil.convertTask(player, pt.getTask()));
			}
		}
		// (4)可以接的任务
		for (Task task : player.getAvailableTaskList(dictManager.getTaskList())) {
			if (task.getApplyNpcId() == npc.getId()) {
				taskObjs.add(taskUtil.convertTask(player, task));
			}
		}
		// (5)反馈消息给客户端
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("taskList", taskObjs);
		netTransceiver.sendMessage(session, msgSent);
	}

}
