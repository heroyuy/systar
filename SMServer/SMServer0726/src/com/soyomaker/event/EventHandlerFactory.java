package com.soyomaker.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.common.GameObject;

public class EventHandlerFactory {
	private Map<String, List<IEventHandler>> handlerMap = new HashMap<String, List<IEventHandler>>();

	public void addFirstHandler(String type, IEventHandler handler) {
		List<IEventHandler> handlers = handlerMap.get(type);
		if (handlers == null) {
			handlers = new ArrayList<IEventHandler>();
			handlerMap.put(type, handlers);
		}
		handlers.add(0, handler);
	}

	public void addHandler(String type, IEventHandler handler) {
		List<IEventHandler> handlers = handlerMap.get(type);
		if (handlers == null) {
			handlers = new ArrayList<IEventHandler>();
			handlerMap.put(type, handlers);
		}
		handlers.add(handler);
	}

	public void handleMessage(GameObject event) {
		String type = event.getType();
		List<IEventHandler> handlers = handlerMap.get(type);
		if (handlers != null) {
			for (IEventHandler h : handlers) {
				if (h.handleEvent(event) == false) {
					return;
				}
			}
		}
	}

	public void removeHandler(String type, IEventHandler handler) {
		List<IEventHandler> handlers = handlerMap.get(type);
		if (handlers != null) {
			handlers.remove(handler);
		}
	}

}
