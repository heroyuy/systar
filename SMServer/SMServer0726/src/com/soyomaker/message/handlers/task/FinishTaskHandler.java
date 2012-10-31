package com.soyomaker.message.handlers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessageSender;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

/**
 * 完成任务
 * 
 * @author wp_g4
 * 
 */
@Component("finishTaskHandler")
public class FinishTaskHandler extends AbHandler {

	@Autowired
	private MessageSender messageSender;
	
	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// TODO Auto-generated method stub

		// 触发更新NPC状态
		messageSender.updateNPCStatus(session);
	}

}
