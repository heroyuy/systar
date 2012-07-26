package com.soyomaker.application;

import com.soyomaker.data.IGObject;

/**
 * 服务模块
 * 
 * @author Administrator
 * 
 */
public interface IService {
	/**
	 * 向服务发送指令
	 * 
	 * @param command
	 */
	public void doCommand(IGObject command);

	/**
	 * 获得服务的状态
	 * 
	 * @return
	 */
	public IGObject getStatus();

	/**
	 * 启动服务
	 */
	public void start();

	/**
	 * 停止服务
	 */
	public void stop();
}
