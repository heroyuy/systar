package com.soyomaker.message.handlers.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.rribbit.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.event.EventIdConst;
import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessageSender;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;

@Component("noticeHandler")
public class NoticeHandler extends AbHandler {

	private static final String DATE_TYPE = "yyyy-MM-dd HH:mm";

	@Autowired
	private MessageSender messageSender;

	@Autowired
	private UserSessionManager userSessionManager;

	@Listener(hint = EventIdConst.EventIdNoticePolling)
	public void pollingNotice() {

		// List<Message> messages = messageService.findUnreadNotification();
		// // TODO ConcurrentException等后期处理
		// Collection<UserSession> userSessions = sessionManager
		// .getAllUserSession();
		// for (UserSession session : userSessions) {
		// for (Message msg : messages) {
		// GameObject go = new GameObject(Protocol.PUSH_MESSAGE);
		// go.putString("title", msg.getTitle());
		// go.putString("content", msg.getContent());
		// go.putString("date", formatDate(msg.getTime(), DATE_TYPE));
		// netTransceiver.sendMessage(session, go);
		// }
		// }
	}

	@Override
	public void handleMessage(UserSession session, GameObject msg) {

		netTransceiver.sendMessage(session, msg);

		/*
		 * GameObject pushMessageObject=new GameObject();
		 * pushMessageObject.setType(Protocol.PUSH_MESSAGE);
		 * pushMessageObject.putString("title", value);
		 * pushMessageObject.putString("content", player.getName());
		 * pushMessageObject.putString("date", content);
		 */

		// //获得聊天信息的序列号
		// int sn = msg.getInt(SN_KEY);
		// String content = msg.getString("content");
		// Player player = session.getUser().getPlayer();
		// GameObject msgSent = new GameObject(msg.getType());
		// msgSent.putInt("playerId", player.getId());
		// msgSent.putString("playerName", player.getName());
		// msgSent.putString("content", content);
		// Collection<UserSession> userSessions = userSessionManager
		// .getAllUserSession();
		// for (UserSession userSession : userSessions) {
		// if (userSession.equals(session)) {
		// msgSent.putInt(SN_KEY, sn);
		// } else {
		// msgSent.remove(SN_KEY);
		// }
		// netTransceiver.sendMessage(userSession, msgSent);
		// }
	}

	/**
	 * 根据收到的包构造回复包，初始化回复包的序列号和协议ID
	 * 
	 * @param msgReceived
	 *            收到的包
	 */
	public GameObject buildResponsePackage(GameObject msgReceived) {
		GameObject msgSent = new GameObject();
		msgSent.setType(msgReceived.getType());
		msgSent.putInt(SN_KEY, msgReceived.getInt(SN_KEY));
		return msgSent;
	}

	private String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

}
