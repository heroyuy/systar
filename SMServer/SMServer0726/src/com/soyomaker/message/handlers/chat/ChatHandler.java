package com.soyomaker.message.handlers.chat;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;

@Component("chatHandler")
public class ChatHandler extends AbHandler {

	@Autowired
	private UserSessionManager userSessionManager;

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		String content = msg.getString("content");
		Player player = session.getUser().getPlayer();
		GameObject msgResponse = this.buildResponsePackage(msg);
		msgResponse.putInt("playerId", player.getId());
		msgResponse.putString("playerName", player.getName());
		msgResponse.putString("content", content);
		GameObject msgPush = this.buildPushPackage(msg);
		msgPush.putInt("playerId", player.getId());
		msgPush.putString("playerName", player.getName());
		msgPush.putString("content", content);
		Collection<UserSession> userSessions = userSessionManager
				.getAllUserSession();
		for (UserSession userSession : userSessions) {
			if (userSession.equals(session)) {
				netTransceiver.sendMessage(userSession, msgResponse);
			} else {
				netTransceiver.sendMessage(userSession, msgPush);
			}
		}
	}

}
