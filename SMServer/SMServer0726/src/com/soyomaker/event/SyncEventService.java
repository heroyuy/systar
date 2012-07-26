package com.soyomaker.event;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.GUObject;
import com.soyomaker.data.IGUObject;

public class SyncEventService extends AbstractBean implements IEventService {
	private EventHandlerFactory handlerFactory = null;

	@Override
	public void doCommand(IGUObject command) {
	}

	public void fireEvent(GUObject e) {
		handlerFactory.handleMessage(e);
	}

	public EventHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	@Override
	public IGUObject getStatus() {
		// TODO Auto-generated method stub
		return null;
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
