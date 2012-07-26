package com.soyomaker.event;

import com.soyomaker.application.IService;
import com.soyomaker.core.GUObject;

public interface IEventService extends IService {
	public void fireEvent(GUObject e);
}
