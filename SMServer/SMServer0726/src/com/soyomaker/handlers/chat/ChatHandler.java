package com.soyomaker.handlers.chat;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.Player;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;
import com.soyomaker.net.UserSessionManager;

@Component("chatHandler")
public class ChatHandler extends AbHandler {

	@Autowired
	private UserSessionManager userSessionManager;

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		int sn = msg.getInt(SN_KEY);
		String content = msg.getString("content");
		Player player = session.getUser().getPlayer();
		GameObject msgSent = new GameObject();
		msgSent.setType(msg.getType());
		msgSent.putInt("playerId", player.getId());
		msgSent.putString("playerName", player.getName());
		msgSent.putString("content", content);
		Collection<UserSession> userSessions = userSessionManager
				.getAllUserSession();
		for (UserSession userSession : userSessions) {
			if (userSession.equals(session)) {
				msgSent.putInt(SN_KEY, sn);
			} else {
				msgSent.remove(SN_KEY);
			}
			netTransceiver.sendMessage(userSession, msgSent);
		}
	}

}
