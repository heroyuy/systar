package com.gudigital.event;

import com.gudigital.core.GUObject;

public interface IEventHandler {
	/**
	 * 处理消息
	 * 
	 * @param event
	 * @return true继续执行其他消息处理类；false消息处理链
	 */
	public boolean handleEvent(GUObject event);
}
