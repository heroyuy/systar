package com.soyomaker.event;

import com.soyomaker.common.GameObject;
import com.soyomaker.common.IService;

public interface IEventService extends IService {
	public void fireEvent(GameObject e);
}
