package com.soyomaker.handlers.login;

import com.soyomaker.dao.DaoManager;
import com.soyomaker.dao.IDao;
import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

public class RegisterHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查包是否完整
		if (username == null || password == null) {
			return;
		}
		// (2)检查用户名长度
		if (username.length() < 3) {
			this.sendMessage(session, msg.getType(), false, "用户名长度不能小于3");
			return;
		}
		// (3)检查密码长度
		if (password.equals("")) {
			this.sendMessage(session, msg.getType(), false, "密码不能为空");
			return;
		}
		// (4)检查用户名是否已经存在
		IDao<User> userDao = DaoManager.getInatance().getDao(User.class);
		User user = userDao.get(username);
		if (user != null) {
			this.sendMessage(session, msg.getType(), false, "用户名已存在");
			return;
		}
		// (5)添加用户到数据库
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean status = userDao.insert(user);
		this.sendMessage(session, msg.getType(), status, status ? "注册成功"
				: "注册失败");
	}

}
