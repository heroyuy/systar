package com.soyomaker.message.handlers.npc;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.DictManager;
import com.soyomaker.model.MapData;
import com.soyomaker.model.Npc;
import com.soyomaker.model.Player;
import com.soyomaker.model.task.PlayerTask;
import com.soyomaker.model.task.Task;
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
		Collection<GameObject> stateList = new ArrayList<GameObject>();
		for (Npc npc : mapData.getNpcList()) {
			if (npc.getType() == Npc.TYPE_NORMAL) {
				// (2)检查NPC的状态
				int state = this.checkNpcState(player, npc);
				GameObject stateObj = new GameObject();
				stateObj.putInt("npcId", npc.getId());
				stateObj.putInt("state", state);
				stateList.add(stateObj);
			}
		}
		// (3)返回消息给客户端
		GameObject msgSent = new GameObject(msg.getType());
		msgSent.putObjectArray("stateList", stateList);
		netTransceiver.sendMessage(session, msgSent);
	}

	/**
	 * 获取npc对于玩家的状态，“进行任务”状态优先
	 * 
	 * @param player
	 *            玩家
	 * @param npc
	 *            npc
	 * @return 状态
	 */
	public int checkNpcState(Player player, Npc npc) {
		int state = Npc.STATE_NORMAL;
		// 检查NPC是否处于STATE_PROCEED_TASK状态
		for (PlayerTask pt : player.getUnfinishedTaskList()) {
			// 以下两种情况 NPC处于 STATE_PROCEED_TASK 状态
			// (1)任务步骤已经结束并且在此NPC处完成任务
			if (pt.isStepOver() && pt.getTask().getFinishNpcId() == npc.getId()) {
				state = Npc.STATE_PROCEED_TASK;
				break;
			}
			// (2)任务步骤未结束并且在此NPC处进行当前步骤
			if (!pt.isStepOver()
					&& pt.getCurTaskStep().getNpcId() == npc.getId()) {
				state = Npc.STATE_PROCEED_TASK;
				break;
			}
		}
		if (state == Npc.STATE_NORMAL) {
			// 检查NPC是否处于STATE_PROCEED_TASK状态
			for (Task task : player.getAvailableTaskList(dictManager
					.getTaskList())) {
				// 有可申请的任务在此NPC处申请
				if (task.getApplyNpcId() == npc.getId()) {
					state = Npc.STATE_APPLY_TASK;
					break;
				}
			}
		}
		return state;
	}
}
