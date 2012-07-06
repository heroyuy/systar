package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 * 画笔
 * 
 * @author wp_g4
 */
public class Painter {

	/** default text size is 12 */
	public static final int DEFAULT_TEXT_SIZE = 12;

	private Color color = null;

	private int textSize = 0;

	/** The fonts to draw to the screen */
	private UnicodeFont font = null;

	public Painter() {
		this.setTextSize(DEFAULT_TEXT_SIZE);
	}

	/**
	 * 绘制图片
	 * 
	 * @param img
	 *            要绘制的图片
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 * @param anchor
	 *            锚点
	 */
	public void drawImage(Image img, int x, int y, int anchor) {

	}

	/**
	 * 绘制直线
	 * 
	 * @param x1
	 *            起点 x 坐标
	 * @param y1
	 *            起点 y 坐标
	 * @param x2
	 *            终点 x 坐标
	 * @param y2
	 *            终点 y 坐标
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		glBegin(GL11.GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}

	/**
	 * 绘制矩形区域
	 * 
	 * @param x
	 *            矩形区域左上角 x 坐标
	 * @param y
	 *            矩形区域左上角 y 坐标
	 * @param width
	 *            矩形区域宽度
	 * @param height
	 *            矩形区域高度
	 */
	public void drawRect(int x, int y, int width, int height) {
	}

	/**
	 * 绘制字符串
	 * 
	 * @param str
	 *            要绘制的字符串
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawString(String str, int x, int y) {
		font.drawString(x, y, str, org.newdawn.slick.Color.yellow);
	}

	/**
	 * 填充矩形区域
	 * 
	 * @param x
	 *            矩形区域左上角 x 坐标
	 * @param y
	 *            矩形区域左上角 y 坐标
	 * @param width
	 *            矩形区域宽度
	 * @param height
	 *            矩形区域高度
	 */
	public void fillRect(int x, int y, int width, int height) {
	}

	/**
	 * 获取当前画笔颜色
	 * 
	 * @return 当前画笔颜色
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * 获取绘制文字的尺寸
	 * 
	 * @return 绘制文字的尺寸
	 */
	public int getTextSize() {
		return this.textSize;
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(Color color) {
		this.color = color;
		glColor3f(1.0f * color.getRed() / 255, 1.0f * color.getGreen() / 255, 1.0f * color.getBlue() / 255);
	}

	/**
	 * 设置文字尺寸
	 * 
	 * @param size
	 *            绘制文字的尺寸
	 */
	public void setTextSize(int size) {
		this.textSize = size;
		font = new UnicodeFont(new Font("黑体", Font.PLAIN, this.getTextSize()));
		// Requires at least one effect
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.addGlyphs("fps:0123456789");
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取字符串的宽度
	 * 
	 * @param str
	 *            要测量的字符串
	 * @return 字符串的宽度
	 */
	public int stringWidth(String str) {
		return font.getWidth(str);
	}

}
