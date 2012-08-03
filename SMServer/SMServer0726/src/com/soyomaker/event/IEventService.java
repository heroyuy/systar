package com.soyomaker.event;

import com.soyomaker.lang.GameObject;
import com.soyomaker.lang.IService;

public interface IEventService extends IService {
	public void fireEvent(GameObject e);
}
