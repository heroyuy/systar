package com.soyomaker.handlers;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.AbHandler;
import com.soyomaker.net.UserSession;

@Component("testHandler")
public class TestHandler extends AbHandler {

	@Override
	public void handleMessage(UserSession session, GameObject msg) {
		// 调试用接口，直接返回客户端发来的包
		netTransceiver.sendMessage(session, msg);
		String command=msg.getString("command");
		if (command.equalsIgnoreCase("shutdown")) {
			try {
				Runtime.getRuntime().exec("shutdown -s");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
