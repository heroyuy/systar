package com.soyomaker.event;

import com.soyomaker.application.IService;
import com.soyomaker.data.GUObject;

public interface IEventService extends IService {
	public void fireEvent(GUObject e);
}
