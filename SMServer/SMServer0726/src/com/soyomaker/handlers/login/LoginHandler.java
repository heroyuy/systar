package com.soyomaker.handlers.login;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.model.dao.DaoManager;
import com.soyomaker.model.dao.IDao;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;
import com.soyomaker.net.PlayerSessionManager;

public class LoginHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查包是否完整
		if (username == null || password == null) {
			return;
		}
		// (2)取用户名为 userName 的帐户
		IDao<User> userDao = DaoManager.getInatance().getDao(User.class);
		User user = userDao.get(username);
		if (user == null) {
			this.sendMessage(playerSession, msg.getType(), false, "帐号不存在", -1);
			return;
		}
		// (3)验证
		if (!user.getPassword().equals(password)) {
			this.sendMessage(playerSession, msg.getType(), false, "密码不正确", -1);
			return;
		}
		// (4)登录成功
		playerSession.setLogin(true);
		playerSession.setUserId(user.getId());
		PlayerSessionManager.getInstance().putPlayerSession(user.getId(),
				playerSession);
		this.sendMessage(playerSession, msg.getType(), true, "登录成功",
				user.getId());
	}

	private void sendMessage(PlayerSession playerSession, String type,
			boolean status, String message, long userId) {
		GameObject msgSent = new GameObject();
		msgSent.setType(type);
		msgSent.putBool("status", status);
		msgSent.putString("msg", message);
		if (userId >= 0) {
			msgSent.putLong("uid", userId);
		}
		NetTransceiver.getInstance().sendMessage(playerSession, msgSent);
	}
}
