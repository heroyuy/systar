package com.soyomaker.message.handlers.chat;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.rribbit.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.event.EventIdConst;
import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.message.Message;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;
import com.soyomaker.service.MessageService;

@Component("noticeHandler")
public class NoticeHandler extends AbHandler {

	private static final String DATE_TYPE = "yyyy-MM-dd HH:mm";

	@Autowired
	private MessagePusher messagePusher;

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserSessionManager sessionManager;

	@Listener(hint = EventIdConst.EventIdNoticePolling)
	public void pollingNotice() {
		messagePusher.sendNotice();
	}

	@Override
	public void doRequest(UserSession sessaion, GameObject msg) {

		List<Message> messages = messageService.findUnreadNotification();
		// TODO ConcurrentException等后期处理
		Collection<UserSession> userSessions = sessionManager
				.getAllUserSession();
		GameObject msgPush = this.buildPushPackage(msg);
		for (Message message : messages) {
			msgPush.putString("title", message.getTitle());
			msgPush.putString("content", message.getContent());
			msgPush.putString("date", formatDate(message.getTime(), DATE_TYPE));
			for (UserSession us : userSessions) {
				netTransceiver.sendMessage(us, msgPush);
			}
		}
	}

	private String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

}
