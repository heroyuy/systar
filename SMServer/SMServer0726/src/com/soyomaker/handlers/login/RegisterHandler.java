package com.soyomaker.handlers.login;

import java.util.ArrayList;
import java.util.List;

import com.soyomaker.data.SMObject;
import com.soyomaker.data.SMObject;
import com.soyomaker.model.Model;
import com.soyomaker.model.db.TableProxy;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;

public class RegisterHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, SMObject msg) {
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
		TableProxy accountProxy = Model.getInstance().getTableProxy("account");
		SMObject account = accountProxy
				.selectSingleWhere("username", username);
		if (account != null) {
			this.sendMessage(playerSession, msg.getType(), false, "用户名已存在");
			return;
		}
		// (5)添加用户到数据库
		List<String> keys = new ArrayList<String>();
		keys.add("username");
		keys.add("password");
		boolean status = accountProxy.insert(keys, msg);
		this.sendMessage(playerSession, msg.getType(), status, status ? "注册成功"
				: "注册失败");
	}

	private void sendMessage(PlayerSession playerSession, String type,
			boolean status, String message) {
		SMObject msgSent = new SMObject();
		msgSent.setType(type);
		msgSent.putBool("status", status);
		msgSent.putString("msg", message);
		NetTransceiver.getInstance().sendMessage(playerSession, msgSent);
	}
}
