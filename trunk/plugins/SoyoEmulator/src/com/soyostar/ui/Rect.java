package com.soyostar.ui;

public class Rect {

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;

    public Rect() {
    }

    public Rect(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        if (width < 0 || height < 0) {
            throw new ArithmeticException("width和height的值不能小于0");
        }
        this.width = width;
        this.height = height;
    }

    public Rect clone() {
        return new Rect(this.x, this.y, this.width, this.height);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new ArithmeticException("height的值不能小于0");
        }
        this.height = height;
    }

    public void setWidth(int width) {
        if (width < 0) {
            throw new ArithmeticException("width的值不能小于0");
        }
        this.width = width;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean containsPoint(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }

    public boolean containsPoint(Point point) {
        return containsPoint(point.getX(), point.getY());
    }
}
