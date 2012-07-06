package com.soyomaker.emulator.ui;

/**
 * 点
 * 
 * @author wp_g4
 */
public class Point {

	private float x = 0;

	private float y = 0;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
