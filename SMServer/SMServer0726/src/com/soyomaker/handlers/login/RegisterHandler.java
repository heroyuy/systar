package com.soyomaker.handlers.login;

import com.soyomaker.lang.GameObject;
import com.soyomaker.model.User;
import com.soyomaker.model.dao.DaoManager;
import com.soyomaker.model.dao.IDao;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;

public class RegisterHandler implements IHandler {

	@Override
	public void handleMessage(UserSession playerSession, GameObject msg) {
		String username = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查包是否完整
		if (username == null || password == null) {
			return;
		}
		// (2)检查用户名长度
		if (username.length() < 3) {
			this.sendMessage(playerSession, msg.getType(), false, "用户名长度不能小于3");
			return;
		}
		// (3)检查密码长度
		if (password.equals("")) {
			this.sendMessage(playerSession, msg.getType(), false, "密码不能为空");
			return;
		}
		// (4)检查用户名是否已经存在
		IDao<User> userDao = DaoManager.getInatance().getDao(User.class);
		User user = userDao.get(username);
		if (user != null) {
			this.sendMessage(playerSession, msg.getType(), false, "用户名已存在");
			return;
		}
		// (5)添加用户到数据库
		user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean status = userDao.insert(user);
		this.sendMessage(playerSession, msg.getType(), status, status ? "注册成功"
				: "注册失败");
	}

	private void sendMessage(UserSession playerSession, String type,
			boolean status, String message) {
		GameObject msgSent = new GameObject();
		msgSent.setType(type);
		msgSent.putBool("status", status);
		msgSent.putString("msg", message);
		NetTransceiver.getInstance().sendMessage(playerSession, msgSent);
	}
}
