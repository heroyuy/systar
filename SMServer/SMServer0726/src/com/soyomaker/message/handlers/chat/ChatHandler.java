package com.soyomaker.message.handlers.chat;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.message.MessagePusher;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;

@Component("chatHandler")
public class ChatHandler extends AbHandler {

	@Autowired
	private UserSessionManager userSessionManager;

	@Autowired
	private MessagePusher messagePusher;

	public void doRequest(UserSession session, GameObject msg) {
		String content = msg.getString("content");
		Player player = session.getUser().getPlayer();
		int playerId = player.getId();
		String playerName = player.getName();
		GameObject msgSent = this.buildPackage(msg);
		msgSent.putInt("playerId", playerId);
		msgSent.putString("playerName", playerName);
		msgSent.putString("content", content);
		this.addOperateResultToPackage(msgSent, true, "发送聊天信息成功");
		netTransceiver.sendMessage(session, msgSent);
		// 群发消息给其它玩家
		Collection<UserSession> userSessions = userSessionManager
				.getAllUserSession();
		for (UserSession userSession : userSessions) {
			if (!userSession.equals(session)) {
				messagePusher.sendChatMessage(userSession, playerId,
						playerName, content);
			}
		}
	}

	public void doPush(UserSession session, GameObject msg) {
		netTransceiver.sendMessage(session, msg);
	}

}
