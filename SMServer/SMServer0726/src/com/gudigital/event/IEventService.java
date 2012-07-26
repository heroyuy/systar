package com.gudigital.event;

import com.gudigital.application.IService;
import com.gudigital.core.GUObject;

public interface IEventService extends IService {
	public void fireEvent(GUObject e);
}
