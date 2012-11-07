package com.soyomaker.net;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.session.UserSession;

public interface INetService {

	public void sendMessage(UserSession session, GameObject msg);

	/**
	 * 启动服务
	 */
	public void start();

	/**
	 * 停止服务
	 */
	public void stop();

}
