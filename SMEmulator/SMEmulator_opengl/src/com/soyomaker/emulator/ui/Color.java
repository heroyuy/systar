package com.soyomaker.emulator.ui;

/**
 * 颜色
 * 
 * @author wp_g4
 */
public class Color {

	/**
	 * 黑色
	 */
	public Color BLACK = new Color(0xff000000);

	/**
	 * 蓝色
	 */
	public Color BLUE = new Color(0xff0000ff);
	/**
	 * 青色
	 */
	public Color CYAN = new Color(0xff00ffff);
	/**
	 * 深灰色
	 */
	public Color DKGRAY = new Color(0xff444444);
	/**
	 * 灰色
	 */
	public Color GRAY = new Color(0xff888888);
	/**
	 * 绿色
	 */
	public Color GREEN = new Color(0xff00ff00);
	/**
	 * 浅灰色
	 */
	public Color LTGRAY = new Color(0xffcccccc);
	/**
	 * 洋红色
	 */
	public Color MAGENTA = new Color(0xffff00ff);
	/**
	 * 红色
	 */
	public Color RED = new Color(0xffff0000);
	/**
	 * 白色
	 */
	public Color WHITE = new Color(0xffffffff);
	/**
	 * 黄色
	 */
	public Color YELLOW = new Color(0xffffff00);

	private int argb = 0;

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

	/**
	 * 创建Color对象
	 * 
	 * @param colorString
	 *            颜色字符串，格式为 #ffffffff 或者0xffffffff
	 */
	public Color(String colorString) {
		int offset = 0;
		if (colorString.startsWith("#")) {
			offset = 1;
		} else {
			offset = 2;
		}
		int a = Integer.parseInt(colorString.substring(0 + offset, 2 + offset), 16);
		int r = Integer.parseInt(colorString.substring(2 + offset, 4 + offset), 16);
		int g = Integer.parseInt(colorString.substring(4 + offset, 6 + offset), 16);
		int b = Integer.parseInt(colorString.substring(6 + offset, 8 + offset), 16);
		this.argb = (a << 24) + (r << 16) + (g << 8) + b;
	}

	/**
	 * 获取颜色值的alpha分量
	 * 
	 * @return 颜色值的alpha分量
	 */
	public int getAlpha() {
		return argb >>> 24;
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
	 * 获取颜色值的蓝色分量
	 * 
	 * @return 颜色值的蓝色分量
	 */
	public int getBlue() {
		return argb & 0x000000ff;
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
	 * 获取颜色值的红色分量
	 * 
	 * @return 颜色值的红色分量
	 */
	public int getRed() {
		return (argb & 0x00ff0000) >>> 16;
	}

	public String toString() {
		return "0x" + Integer.toHexString(argb);
	}
}
