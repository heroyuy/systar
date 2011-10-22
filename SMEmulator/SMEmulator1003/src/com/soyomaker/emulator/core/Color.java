package com.soyomaker.emulator.core;

/**
 * 颜色
 * 
 * @author wp_g4
 */
public class Color {

	/**
	 * 黑色
	 */
	public static final int BLACK = 0xff000000;

	/**
	 * 蓝色
	 */
	public static final int BLUE = 0xff0000ff;
	/**
	 * 青色
	 */
	public static final int CYAN = 0xff00ffff;
	/**
	 * 深灰色
	 */
	public static final int DKGRAY = 0xff444444;
	/**
	 * 灰色
	 */
	public static final int GRAY = 0xff888888;
	/**
	 * 绿色
	 */
	public static final int GREEN = 0xff00ff00;
	/**
	 * 浅灰色
	 */
	public static final int LTGRAY = 0xffcccccc;
	/**
	 * 洋红色
	 */
	public static final int MAGENTA = 0xffff00ff;
	/**
	 * 红色
	 */
	public static final int RED = 0xffff0000;
	/**
	 * 白色
	 */
	public static final int WHITE = 0xffffffff;
	/**
	 * 黄色
	 */
	public static final int YELLOW = 0xffffff00;

	/**
	 * 获取颜色值的alpha分量
	 * 
	 * @param color
	 *            颜色值
	 * @return 颜色值的alpha分量
	 */
	public static int getAlpha(int color) {
		return color >>> 24;
	}

	/**
	 * 获取颜色值的蓝色分量
	 * 
	 * @param color
	 *            颜色值
	 * @return 颜色值的蓝色分量
	 */
	public static int getBlue(int color) {
		return color & 0x000000ff;
	}

	public static int getColor(int a, int r, int g, int b) {

		return (a << 24) + (r << 16) + (g << 8) + b;
	}

	/**
	 * 获取颜色值的绿色分量
	 * 
	 * @param color
	 *            颜色值
	 * @return 颜色值的绿色分量
	 */
	public static int getGreen(int color) {
		return (color & 0x0000ff00) >>> 8;
	}

	/**
	 * 获取颜色值的红色分量
	 * 
	 * @param color
	 *            颜色值
	 * @return 颜色值的红色分量
	 */
	public static int getRed(int color) {
		return (color & 0x00ff0000) >>> 16;
	}

	private Color() {
	}
}
