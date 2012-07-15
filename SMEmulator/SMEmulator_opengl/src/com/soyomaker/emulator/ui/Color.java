package com.soyomaker.emulator.ui;

/**
 * 颜色
 * 
 * @author wp_g4
 */
public class Color {

	/**
	 * 透明
	 */
	public static Color NULL = colorFromARGB(0x00000000);

	/**
	 * 黑色
	 */
	public static Color BLACK = colorFromARGB(0xff000000);

	/**
	 * 蓝色
	 */
	public static Color BLUE = colorFromARGB(0xff0000ff);
	/**
	 * 青色
	 */
	public static Color CYAN = colorFromARGB(0xff00ffff);
	/**
	 * 深灰色
	 */
	public static Color DKGRAY = colorFromARGB(0xff444444);
	/**
	 * 灰色
	 */
	public static Color GRAY = colorFromARGB(0xff888888);
	/**
	 * 绿色
	 */
	public static Color GREEN = colorFromARGB(0xff00ff00);
	/**
	 * 浅灰色
	 */
	public static Color LTGRAY = colorFromARGB(0xffcccccc);
	/**
	 * 洋红色
	 */
	public static Color MAGENTA = colorFromARGB(0xffff00ff);
	/**
	 * 红色
	 */
	public static Color RED = colorFromARGB(0xffff0000);
	/**
	 * 白色
	 */
	public static Color WHITE = colorFromARGB(0xffffffff);
	/**
	 * 黄色
	 */
	public static Color YELLOW = colorFromARGB(0xffffff00);

	public static Color colorFromARGB(int argb) {
		float a = (argb >>> 24) / 255.0f;
		float r = ((argb & 0x00ff0000) >>> 16) / 255.0f;
		float g = ((argb & 0x0000ff00) >>> 8) / 255.0f;
		float b = (argb & 0x000000ff) / 255.0f;
		return new Color(r, g, b, a);
	}

	public static Color colorFromARGB(String argbString) {
		int offset = 0;
		if (argbString.startsWith("#")) {
			offset = 1;
		} else {
			offset = 2;
		}
		float a = Integer.parseInt(argbString.substring(0 + offset, 2 + offset), 16) / 255.f;
		float r = Integer.parseInt(argbString.substring(2 + offset, 4 + offset), 16) / 255.f;
		float g = Integer.parseInt(argbString.substring(4 + offset, 6 + offset), 16) / 255.f;
		float b = Integer.parseInt(argbString.substring(6 + offset, 8 + offset), 16) / 255.f;
		return new Color(r, g, b, a);
	}

	public static Color colorFromRGBA(int rgba) {
		float r = (rgba >>> 24) / 255.0f;
		float g = ((rgba & 0x00ff0000) >>> 16) / 255.0f;
		float b = ((rgba & 0x0000ff00) >>> 8) / 255.0f;
		float a = (rgba & 0x000000ff) / 255.0f;
		return new Color(r, g, b, a);
	}

	public static Color colorFromRGBA(String rgbaString) {
		int offset = 0;
		if (rgbaString.startsWith("#")) {
			offset = 1;
		} else {
			offset = 2;
		}
		float r = Integer.parseInt(rgbaString.substring(0 + offset, 2 + offset), 16) / 255.f;
		float g = Integer.parseInt(rgbaString.substring(2 + offset, 4 + offset), 16) / 255.f;
		float b = Integer.parseInt(rgbaString.substring(4 + offset, 6 + offset), 16) / 255.f;
		float a = Integer.parseInt(rgbaString.substring(6 + offset, 8 + offset), 16) / 255.f;
		return new Color(r, g, b, a);
	}

	/**
	 * red分量
	 */
	private float red = 0.0f;

	/**
	 * green分量
	 */
	private float green = 0.0f;

	/**
	 * blue分量
	 */
	private float blue = 0.0f;

	/**
	 * alpha分量
	 */
	private float alpha = 0.0f;

	public Color(float r, float g, float b, float a) {
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
	}

	/**
	 * 获取颜色值的alpha分量
	 * 
	 * @return 颜色值的alpha分量
	 */
	public float getAlpha() {
		return this.alpha;
	}

	/**
	 * 获取颜色值的蓝色分量
	 * 
	 * @return 颜色值的蓝色分量
	 */
	public float getBlue() {
		return this.blue;
	}

	/**
	 * 获取颜色值的绿色分量
	 * 
	 * @return 颜色值的绿色分量
	 */
	public float getGreen() {
		return this.green;
	}

	/**
	 * 获取颜色值的红色分量
	 * 
	 * @return 颜色值的红色分量
	 */
	public float getRed() {
		return this.red;
	}

	public String toString() {
		return "(r=" + this.red + " g=" + this.green + " b=" + this.blue + " a=" + this.alpha + ")";
	}
}
