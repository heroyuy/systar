package com.soyomaker.event;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.GObject;
import com.soyomaker.data.IGObject;

public class SyncEventService extends AbstractBean implements IEventService {
	private EventHandlerFactory handlerFactory = null;

	@Override
	public void doCommand(IGObject command) {
	}

	public void fireEvent(GObject e) {
		handlerFactory.handleMessage(e);
	}

	public EventHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	@Override
	public IGObject getStatus() {
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
