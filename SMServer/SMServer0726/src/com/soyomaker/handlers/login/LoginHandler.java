package com.soyomaker.handlers.login;

import com.soyomaker.data.SMObject;
import com.soyomaker.data.SMObject;
import com.soyomaker.model.Model;
import com.soyomaker.model.db.TableProxy;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;
import com.soyomaker.net.PlayerSessionManager;

public class LoginHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, SMObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查包是否完整
		if (username == null || password == null) {
			return;
		}
		// (2)取用户名为 userName 的帐户
		TableProxy accountProxy = Model.getInstance().getTableProxy("account");
		SMObject account = accountProxy
				.selectSingleWhere("username", username);
		if (account == null) {
			this.sendMessage(playerSession, msg.getType(), false, "帐号不存在", -1);
			return;
		}
		// (3)验证
		if (!account.getString("password").equals(password)) {
			this.sendMessage(playerSession, msg.getType(), false, "密码不正确", -1);
			return;
		}
		// (4)登录成功
		int playerId = account.getInt("id");
		playerSession.setLogin(true);
		playerSession.setPlayerId(playerId);
		PlayerSessionManager.getInstance().putPlayerSession(playerId,
				playerSession);
		this.sendMessage(playerSession, msg.getType(), true, "登录成功", playerId);
	}

	private void sendMessage(PlayerSession playerSession, String type,
			boolean status, String message, int playerId) {
		SMObject msgSent = new SMObject();
		msgSent.setType(type);
		msgSent.putBool("status", status);
		msgSent.putString("msg", message);
		if (playerId > 0) {
			msgSent.putInt("playerId", playerId);
		}
		NetTransceiver.getInstance().sendMessage(playerSession, msgSent);
	}
}
