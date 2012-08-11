package com.soyomaker.handlers.login;

import com.soyomaker.dao.DaoManager;
import com.soyomaker.dao.impl.UserDao;
import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;
import com.soyomaker.net.UserSessionManager;

public class LoginHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查包是否完整
		if (username == null || password == null) {
			return;
		}
		// (2)取用户名为 userName 的帐户
		UserDao userDao = (UserDao) DaoManager.getInatance().getDao(User.class);
		User user = userDao.getUser(username);
		if (user == null) {
			this.sendMessage(session, msg.getType(), false, "帐号不存在");
			return;
		}
		// (3)验证
		if (!user.getPassword().equals(password)) {
			this.sendMessage(session, msg.getType(), false, "密码不正确");
			return;
		}
		// (4)登录成功
		session.setLogin(true);
		session.setUserId(user.getId());
		UserSessionManager.getInstance().putUserSession(user.getId(), session);
		GameObject msgSent = this.buildPackage(msg.getType(), true, "登录成功");
		msg.putLong("userId", user.getId());
		NetTransceiver.getInstance().sendMessage(session, msgSent);
	}

}
