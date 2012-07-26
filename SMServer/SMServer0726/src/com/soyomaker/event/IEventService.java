package com.soyomaker.event;

import com.soyomaker.application.IService;
import com.soyomaker.data.GObject;

public interface IEventService extends IService {
	public void fireEvent(GObject e);
}
