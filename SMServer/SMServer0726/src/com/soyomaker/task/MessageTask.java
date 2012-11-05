package com.soyomaker.task;

import org.rribbit.RequestResponseBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soyomaker.event.EventIdConst;

/**
 * 系统公告Task
 * 
 * @author chenwentao
 * 
 */
@Component("messageTask")
public class MessageTask {

	@Autowired
	private RequestResponseBus rrb;

	@Scheduled(fixedDelay = 10000)
	public void sendNotification() {
		rrb.send(EventIdConst.EventIdNoticePolling);
	}
}
