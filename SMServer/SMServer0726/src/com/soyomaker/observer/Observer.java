package com.soyomaker.observer;

/**
 * 观察者接口
 * 
 * @author cokey
 */
public interface Observer {

	/**
	 * 处理事件
	 * 
	 * @param event
	 *            事件
	 */
	public void handleEvent(Event event);
}
