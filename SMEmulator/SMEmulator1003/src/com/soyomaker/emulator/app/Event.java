package com.soyomaker.emulator.app;

public class Event {

	public static final int EVENT_TYPE_DOWN = 0;

	public static final int EVENT_TYPE_MOVE = 1;

	public static final int EVENT_TYPE_UP = 2;

	private int x = -1;

	private int y = -1;

	private int type = -1;

	public Event(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}

	protected int getType() {
		return type;
	}
}
