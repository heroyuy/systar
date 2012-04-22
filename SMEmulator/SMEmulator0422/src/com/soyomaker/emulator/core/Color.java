package com.soyomaker.emulator.core;

/**
 * 颜色
 * 
 * @author wp_g4
 */
public class Color {

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
