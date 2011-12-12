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
	public static final Color BLACK = new Color(0xff000000);

	/**
	 * 蓝色
	 */
	public static final Color BLUE = new Color(0xff0000ff);
	/**
	 * 青色
	 */
	public static final Color CYAN = new Color(0xff00ffff);
	/**
	 * 深灰色
	 */
	public static final Color DKGRAY = new Color(0xff444444);
	/**
	 * 灰色
	 */
	public static final Color GRAY = new Color(0xff888888);
	/**
	 * 绿色
	 */
	public static final Color GREEN = new Color(0xff00ff00);
	/**
	 * 浅灰色
	 */
	public static final Color LTGRAY = new Color(0xffcccccc);
	/**
	 * 洋红色
	 */
	public static final Color MAGENTA = new Color(0xffff00ff);
	/**
	 * 红色
	 */
	public static final Color RED = new Color(0xffff0000);
	/**
	 * 白色
	 */
	public static final Color WHITE = new Color(0xffffffff);
	/**
	 * 黄色
	 */
	public static final Color YELLOW = new Color(0xffffff00);

	private int argb = 0;

	/**
	 * 获取颜色值的alpha分量
	 * 
	 * @return 颜色值的alpha分量
	 */
	public int getAlpha() {
		return argb >>> 24;
	}

	/**
	 * 获取颜色值的红色分量
	 * 
	 * @return 颜色值的红色分量
	 */
	public int getRed() {
		return (argb & 0x00ff0000) >>> 16;
	}

	/**
	 * 获取颜色值的绿色分量
	 * 
	 * @return 颜色值的绿色分量
	 */
	public int getGreen() {
		return (argb & 0x0000ff00) >>> 8;
	}

	/**
	 * 获取颜色值的蓝色分量
	 * 
	 * @return 颜色值的蓝色分量
	 */
	public int getBlue() {
		return argb & 0x000000ff;
	}

	/**
	 * 获取argb值
	 * 
	 * @return argb值
	 */
	public int getArgb() {
		return argb;
	}

	/**
	 * 创建Color对象
	 * 
	 * @param argb
	 *            argb值
	 */
	public Color(int argb) {
		this.argb = argb;
	}

	/**
	 * 创建Color对象
	 * 
	 * @param a
	 *            alpha分量
	 * @param r
	 *            red分量
	 * @param g
	 *            green分量
	 * @param b
	 *            blue分量
	 */
	public Color(int a, int r, int g, int b) {
		this.argb = (a << 24) + (r << 16) + (g << 8) + b;
	}
}
