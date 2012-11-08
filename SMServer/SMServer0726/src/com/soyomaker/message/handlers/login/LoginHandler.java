package com.soyomaker.message.handlers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.config.Config;
import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.net.session.UserSessionManager;
import com.soyomaker.service.UserService;

@Component("loginHandler")
public class LoginHandler extends AbHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private UserSessionManager userSessionManager;

	public boolean checkRequestPackage(GameObject msg) {
		return msg.containsKey("username") && msg.containsKey("password");
	}

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		GameObject msgSent = this.buildPackage(msg);
		// (1)取用户名为 userName 的帐户
		User user = userService.findByUsername(username);
		if (user == null) {
			this.addOperateResultToPackage(msgSent, false, "帐号不存在");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (2)验证
		if (Config.isDebug() == false) {
			if (!user.getPassword().equals(password)) {
				this.addOperateResultToPackage(msgSent, false, "密码不正确");
				netTransceiver.sendMessage(session, msgSent);
				return;
			}
		}
		// (3)登录成功
		session.setUser(user);
		userSessionManager.putUserSession(user.getId(), session);
		this.addOperateResultToPackage(msgSent, true, "登录成功");
		msgSent.putLong("userId", user.getId());
		netTransceiver.sendMessage(session, msgSent);
	}

}