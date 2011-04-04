package com.soyostar.ui;

public class Circle {

	private int x = 0;

	private int y = 0;

	private int radius = 0;

	public Circle() {

	}

	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		if (radius < 0) {
			throw new ArithmeticException("radius的值不能小于0");
		}
		this.radius = radius;
	}

	public Circle clone() {
		return new Circle(this.x, this.y, this.radius);
	}

	public int getRadius() {
		return radius;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setRadius(int radius) {
		if (radius < 0) {
			throw new ArithmeticException("radius的值不能小于0");
		}
		this.radius = radius;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
