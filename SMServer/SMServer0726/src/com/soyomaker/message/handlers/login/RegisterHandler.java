package com.soyomaker.message.handlers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.session.UserSession;
import com.soyomaker.service.UserService;

@Component("registerHandler")
public class RegisterHandler extends AbHandler {

	@Autowired
	private UserService userService;

	public boolean checkRequestPackage(GameObject msg) {
		return msg.containsKey("username") && msg.containsKey("password");
	}

	@Override
	public void doRequest(UserSession session, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		GameObject msgSent = this.buildPackage(msg);
		// (2)检查用户名长度
		if (username.length() < 3) {
			this.addOperateResultToPackage(msgSent, false, "用户名长度不能小于3");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (3)检查密码长度
		if (password.equals("")) {
			this.addOperateResultToPackage(msgSent, false, "密码不能为空");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (4)检查用户名是否已经存在
		User user = userService.findByUsername(username);
		if (user != null) {
			this.addOperateResultToPackage(msgSent, false, "用户名已存在");
			netTransceiver.sendMessage(session, msgSent);
			return;
		}
		// (5)添加用户到数据库
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean status = userService.save(user);
		this.addOperateResultToPackage(msgSent, status, status ? "注册成功"
				: "注册失败");
		netTransceiver.sendMessage(session, msgSent);
	}

}