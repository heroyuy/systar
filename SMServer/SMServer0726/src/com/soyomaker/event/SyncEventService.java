package com.soyomaker.event;

import com.soyomaker.common.AbstractBean;
import com.soyomaker.data.SMObject;

public class SyncEventService extends AbstractBean implements IEventService {
	private EventHandlerFactory handlerFactory = null;

	public void fireEvent(SMObject e) {
		handlerFactory.handleMessage(e);
	}

	public EventHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	public void setHandlerFactory(EventHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}
}
