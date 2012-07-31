package com.soyomaker.handlers.login;

import org.apache.log4j.Logger;

import com.soyomaker.data.ISMObject;
import com.soyomaker.model.Model;
import com.soyomaker.model.db.TableProxy;
import com.soyomaker.net.IHandler;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;

public class RegisterHandler implements IHandler {

	@Override
	public void handleMessage(PlayerSession playerSession, ISMObject msg) {
		Logger log = Logger.getLogger(getClass());
		String userName = msg.getString("username");
		String password = msg.getString("password");
		// (1)检查用户名是否合法
		if (userName == null) {
			return;
		}
		if (userName.length() < 6) {
			return;
		}
		// (2)检查用户名是否已存在
		TableProxy accountProxy = Model.getInstance().getTableProxy("account");
		accountProxy.insert(msg);
		// (3)添加用户到数据库
		NetTransceiver.getInstance().sendMessage(playerSession, msg);
	}
}
