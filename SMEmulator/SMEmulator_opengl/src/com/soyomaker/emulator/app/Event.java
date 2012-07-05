package com.soyomaker.emulator.app;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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

	public int getType() {
		return type;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Event relativeEvent(double x, double y) {
		return new Event(this.getX() + x, this.getY() + y, this.getType());
	}

	public void scale(double scale, double x, double y) {
		AffineTransform at = new AffineTransform();
		at.translate((1 - scale) * x, (1 - scale) * y);
		at.scale(scale, scale);
		Point2D p = at.transform(new Point2D.Double(this.getX(), this.getY()), null);
		this.x = p.getX();
		this.y = p.getY();
	}

	public String toString() {
		return "Event{x=" + getX() + " y=" + getY() + " type=" + getType() + "}";
	}
}
