package com.soyomaker.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 通知中心类
 * 
 * @author cokey
 */
public class Notifier {

	private static Notifier instance = new Notifier();

	public static Notifier getInstance() {
		return instance;
	}

	private Map<String, Collection<Observer>> observerMap;

	private Notifier() {
		observerMap = new HashMap<String, Collection<Observer>>();
		observerMap = Collections.synchronizedMap(observerMap);// 线程安全包装
	}

	public void addObserver(Observer observer, ArrayList<String> commands) {
		for (String command : commands) {
			this.addObserver(observer, command);
		}
	}

	public void addObserver(Observer observer, String command) {
		Collection<Observer> c = observerMap.get(command);
		if (c == null) {
			c = new HashSet<Observer>(); // 禁止重复元素的Collection
			c = Collections.synchronizedCollection(c);// 线程安全包装
			observerMap.put(command, c);
		}
		c.add(observer);
	}

	public void addObserver(Observer observer, String... commands) {
		for (String command : commands) {
			this.addObserver(observer, command);
		}
	}

	public void notifyEvent(String command, Object... param) {
		Event e = Event.createEvent(command, param);
		Collection<Observer> c = observerMap.get(command);
		if (c != null) {
			for (Observer observer : c) {
				observer.handleEvent(e);
			}
		}
	}

	public void removeAllObservers() {
		observerMap.clear();
	}

	public void removeAllObserversForCommand(String command) {
		Collection<Observer> c = observerMap.get(command);
		if (c != null) {
			c.clear();
		}
	}

	public void removeObserverForAllCommands(Observer observer) {
		Set<Map.Entry<String, Collection<Observer>>> set = observerMap
				.entrySet();
		for (Iterator<Map.Entry<String, Collection<Observer>>> it = set
				.iterator(); it.hasNext();) {
			Map.Entry<String, Collection<Observer>> entry = (Map.Entry<String, Collection<Observer>>) it
					.next();
			entry.getValue().remove(observer);
		}
	}

	public void removeObserverForCommand(Observer observer,
			ArrayList<String> commands) {
		for (String command : commands) {
			Collection<Observer> c = observerMap.get(command);
			if (c != null && c.contains(observer)) {
				c.remove(observer);
			}
		}
	}

	public void removeObserverForCommand(Observer observer, String... commands) {
		for (String command : commands) {
			Collection<Observer> c = observerMap.get(command);
			if (c != null && c.contains(observer)) {
				c.remove(observer);
			}
		}
	}

	public void removeObserverForCommand(Observer observer, String command) {
		Collection<Observer> c = observerMap.get(command);
		if (c != null && c.contains(observer)) {
			c.remove(observer);
		}
	}
}
