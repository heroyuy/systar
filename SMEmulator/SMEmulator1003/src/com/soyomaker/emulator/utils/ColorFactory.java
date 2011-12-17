package com.soyomaker.emulator.utils;

public class ColorFactory {

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

	private static ColorFactory instance = new ColorFactory();

	public static ColorFactory getInstance() {
		return instance;
	}

	private ColorFactory() {

	}

	public Color parseInt(int argb) {
		return new Color(argb);
	}

	public Color parseString(String colorString) {
		int offset = 0;
		if (colorString.startsWith("#")) {
			offset = 1;
		} else {
			offset = 2;
		}
		int a = Integer.parseInt(colorString.substring(0 + offset, 2 + offset),
				16);
		int r = Integer.parseInt(colorString.substring(2 + offset, 4 + offset),
				16);
		int g = Integer.parseInt(colorString.substring(4 + offset, 6 + offset),
				16);
		int b = Integer.parseInt(colorString.substring(6 + offset, 8 + offset),
				16);
		return new Color(a, r, g, b);
	}

}
