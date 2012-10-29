package com.soyomaker.observer;

/**
 * 事件类
 * 
 * @author cokey
 */
public class Event {

	/**
	 * 
	 * @param command
	 * @param param
	 * @return
	 */
	public static Event createEvent(String command, Object... param) {
		return new Event(command, param);
	}

	private String command = null;

	private Object[] params = null;

	private Event(String command, Object... param) {
		this.command = command;
		this.params = param;
	}

	public String getCommand() {
		return command;
	}

	public Object[] getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "Event{command=\"" + command + "\" param=" + params.toString()
				+ "}";
	}
}
