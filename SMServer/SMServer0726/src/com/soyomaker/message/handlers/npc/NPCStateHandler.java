package com.soyomaker.message.handlers.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
import com.soyomaker.model.task.TaskStep;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("npcStateHandler")
public class NPCStateHandler extends AbHandler {

	@Autowired
	private DictManager dictManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// (1)取当前地图上的所有npc
		Player player = session.getUser().getPlayer();
		MapData mapData = dictManager.getMapData(player.getMapId());
		Map<Integer, Npc> npcMap = mapData.getNpcMap();
		Collection<GameObject> stateList = new ArrayList<GameObject>();
		for (Npc npc : npcMap.values()) {
			// (2)检查NPC的状态
			int state = this.checkNpcState(player, npc);
			GameObject stateObj = new GameObject();
			stateObj.putInt("npcId", npc.getId());
			stateObj.putInt("state", state);
			stateList.add(stateObj);
		}
		// (3)返回消息给客户端
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("stateList", stateList);
		netTransceiver.sendMessage(session, msgSent);
	}

	public int checkNpcState(Player player, Npc npc) {
		int state = Npc.STATE_NORMAL;
		boolean canFinishTask = false;
		boolean hasDialogue = false;
		Map<Integer, PlayerTask> playerTasks = player.getPlayerTasks();
		for (PlayerTask pt : playerTasks.values()) {
			Task task = dictManager.getTask(pt.getId().getTaskId());
			if (!pt.isFinished()) {
				// 正在进行的任务
				if (pt.getStep() == task.getSteps().size()) {
					// 所有任务步骤已完成
					if (task.getNpcId() == npc.getId()) {
						// “完成任务”的NPC就是当前NPC
						canFinishTask = true;
						// 由于"有任务可完成"优先级最高，故此处可以break
						break;
					}
				} else {
					// 任务步骤未完成
					TaskStep ts = task.getSteps().get(pt.getStep());
					if (ts.getNpcId() == npc.getId()) {
						// 任务步骤NPC就是当前NPC
						hasDialogue = true;
					}
				}
			}
		}
		if (canFinishTask) {
			state = Npc.STATE_FINISH_TASK;
		} else if (hasDialogue) {
			state = Npc.STATE_DIALOGUE;
		}
		if (state == Npc.STATE_NORMAL) {
			// 检查是否有任务可接
			Collection<Task> taskList = dictManager.getTaskList();
			for (Iterator<Task> iterator = taskList.iterator(); iterator
					.hasNext();) {
				Task task = iterator.next();
				if (!player.hasTask(task.getId())
						&& task.getNpcId() == npc.getId()
						&& player.canApplyTask(task)) {
					state = Npc.STATE_DIALOGUE;
					break;
				}
			}
		}
		return Npc.STATE_DIALOGUE;
	}

}
