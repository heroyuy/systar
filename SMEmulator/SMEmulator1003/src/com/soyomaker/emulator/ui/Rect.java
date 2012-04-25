package com.soyomaker.emulator.ui;

/**
 * 矩形
 * 
 * @author wp_g4
 */
public class Rect {

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;

	/**
	 * 构造一个新的矩形，其左上角被指定为 (x,y)，其宽度为 width ，高度为 height 。
	 * 
	 * @param x
	 *            指定的 x 坐标
	 * @param y
	 *            指定的 y 坐标
	 * @param width
	 *            矩形的宽度
	 * @param height
	 *            矩形的高度
	 */
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * 构造一个新的 Rect，并将其初始化，以与指定 Rect 的值匹配。
	 * 
	 * @param rect
	 *            要从中将初始值复制到新构造的 Rect
	 */
	public Rect(Rect rect) {
		this.x = rect.x;
		this.y = rect.y;
		this.width = rect.width;
		this.height = rect.height;
	}

	/**
	 * 获取矩形的高度
	 * 
	 * @return 矩形的高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 获取矩形的宽度
	 * 
	 * @return 矩形的宽度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 获取矩形左上角的点的 x 坐标
	 * 
	 * @return 矩形左上角的点的 x 坐标
	 */
	public int getX() {
		return x;
	}

	/**
	 * 获取矩形左上角的点的 y 坐标
	 * 
	 * @return 矩形左上角的点的 y 坐标
	 */
	public int getY() {
		return y;
	}

	/**
	 * 设置矩形的高度
	 * 
	 * @param height
	 *            新的矩形高度
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 设置矩形的宽度
	 * 
	 * @param width
	 *            新的矩形宽度
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 设置矩形的位置
	 * 
	 * @param x
	 *            新的矩形左上角的点的 x 坐标
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 设置矩形的位置
	 * 
	 * @param y
	 *            新的矩形左上角的点的 y 坐标
	 */
	public void setY(int y) {
		this.y = y;
	}
}
