package com.soyomaker.event;

import com.soyomaker.common.IService;
import com.soyomaker.data.SMObject;

public interface IEventService extends IService {
	public void fireEvent(SMObject e);
}
