package com.soyostar.ui;

public class Line {

	private Point startPoint = null;

	private Point endPoint = null;

	public Line() {
		startPoint = new Point();
		endPoint = new Point();
	}

	public Line(int startX, int startY, int endX, int endY) {
		startPoint = new Point(startX, startY);
		endPoint = new Point(endX, endY);
	}

	public Line(Point startPoint, Point endPoint) {
		if (startPoint == null || endPoint == null) {
			throw new ArithmeticException("startPoint和endPoint的值不能为null");
		}

		this.startPoint = startPoint.clone();
		this.endPoint = endPoint.clone();
	}

	public Point getEndPoint() {
		return endPoint.clone();
	}

	public int getLenght() {
		return endPoint.getDistance(startPoint);
	}

	public Point getStartPoint() {
		return startPoint.clone();
	}

	public void setEndPoint(Point endPoint) {
		if (endPoint == null) {
			throw new ArithmeticException("endPoint的值不能为null");
		}
		this.endPoint = endPoint.clone();
	}

	public void setStartPoint(Point startPoint) {
		if (startPoint == null) {
			throw new ArithmeticException("startPoint的值不能为null");
		}
		this.startPoint = startPoint.clone();
	}

	public Line clone() {
		return new Line(this.startPoint.clone(), this.endPoint.clone());
	}

}
