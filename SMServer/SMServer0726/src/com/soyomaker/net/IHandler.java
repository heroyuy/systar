package com.soyomaker.net;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.session.UserSession;

public interface IHandler {

	/**
	 * 检查push请求的包
	 * 
	 * @param msg
	 * @return
	 */
	public boolean checkPushPackage(GameObject msg);

	/**
	 * 检查客户端请求的包
	 * 
	 * @param msg
	 * @return
	 */
	public boolean checkRequestPackage(GameObject msg);

	/**
	 * 处理push请求
	 * 
	 * @param session
	 * @param msg
	 */
	public void doPush(UserSession session, GameObject msg);

	/**
	 * 处理客户端请求
	 * 
	 * @param session
	 * @param msg
	 */
	public void doRequest(UserSession session, GameObject msg);

}
