package com.soyostar.ui;

public class Point {

	private int x = 0;

	private int y = 0;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point clone() {
		return new Point(this.x, this.y);
	}

	public int getDistance(Point point){
		return (int) Math.sqrt((point.getX() - x) * (point.getX() - x)
				+ (point.getY() - y) * (point.getY() - y));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
