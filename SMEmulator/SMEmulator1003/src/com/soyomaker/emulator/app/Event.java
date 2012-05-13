package com.soyomaker.emulator.app;

public class Event {

	public static final int EVENT_TYPE_DOWN = 0;

	public static final int EVENT_TYPE_MOVE = 1;

	public static final int EVENT_TYPE_UP = 2;

	private double x = -1;

	private double y = -1;

	private int type = -1;

	public Event(double x, double y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Event relativeEvent(double x, double y) {
		return new Event(this.getX() + x, this.getY() + y, this.getType());
	}

	public int getType() {
		return type;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void scale(double scale, double x, double y) {
		this.x = this.x + (1 - scale) * x;
		this.y = this.y + (1 - scale) * y;
		this.x = this.x * scale;
		this.y = this.y * scale;
	}

	public String toString() {
		return "Event{x=" + getX() + " y=" + getY() + " type=" + getType() + "}";
	}
}
