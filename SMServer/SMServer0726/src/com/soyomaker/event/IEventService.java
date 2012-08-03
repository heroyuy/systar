package com.soyomaker.event;

import com.soyomaker.common.IService;
import com.soyomaker.data.GameObject;

public interface IEventService extends IService {
	public void fireEvent(GameObject e);
}
