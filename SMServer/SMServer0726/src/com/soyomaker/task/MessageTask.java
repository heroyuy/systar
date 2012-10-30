package com.soyomaker.task;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.message.Message;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;
import com.soyomaker.net.UserSessionManager;
import com.soyomaker.net.mina.Protocol;
import com.soyomaker.service.MessageService;

/**
 * 系统公告Task
 * 
 * @author chenwentao
 * 
 */
@Component("messageTask")
public class MessageTask {

	private static final String DATE_TYPE = "yyyy-MM-dd HH:mm";

	@Autowired
	private MessageService messageService;

	@Autowired
	private NetTransceiver netTransceiver;

	@Autowired
	private UserSessionManager sessionManager;

	@Scheduled(fixedDelay = 10000)
	public void sendNotification() {
		List<Message> messages = messageService.findUnreadNotification();
		// TODO ConcurrentException等后期处理
		Collection<UserSession> userSessions = sessionManager
				.getAllUserSession();
		for (UserSession session : userSessions) {
			for (Message msg : messages) {
				GameObject go = new GameObject(Protocol.PUSH_MESSAGE);
				go.putString("title", msg.getTitle());
				go.putString("content", msg.getContent());
				go.putString("date", formatDate(msg.getTime(), DATE_TYPE));
				netTransceiver.sendMessage(session, go);
			}
		}
	}

	private String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
