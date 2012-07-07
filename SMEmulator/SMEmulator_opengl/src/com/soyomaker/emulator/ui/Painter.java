package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *  TODO
 *   1.drawString应该优化,在游戏启动时加载所有游戏中会用到的字符
 */

/**
 * 画笔
 * 
 * @author wp_g4
 */
public class Painter {

	/** default text size is 12 */
	public static final int DEFAULT_TEXT_SIZE = 14;

	private Color color = null;

	private int textSize = 0;

	/** The fonts to draw to the screen */
	private UnicodeFont font = null;

	public Painter() {
		this.setTextSize(DEFAULT_TEXT_SIZE);
	}

	/**
	 * 绘制2D纹理
	 * 
	 * @param texture2D
	 *            要绘制的2D纹理
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawTexture2D(Texture2D texture2D, int x, int y) {
		Color c = getColor();
		setColor(Color.WHITE);
		texture2D.bind();
		glBegin(GL11.GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 0);
		glVertex2f(x + texture2D.getTextureWidth(), y);
		glTexCoord2f(1, 1);
		glVertex2f(x + texture2D.getTextureWidth(), y + texture2D.getTextureHeight());
		glTexCoord2f(0, 1);
		glVertex2f(x, y + texture2D.getTextureHeight());
		glEnd();
		setColor(c);
	}

	/**
	 * 绘制2D纹理
	 * 
	 * @param texture2D
	 *            要绘制的2D纹理
	 * @param dx
	 *            绘制区域在纹理上的x坐标
	 * @param dy
	 *            绘制区域在纹理上的y坐标
	 * @param width
	 *            绘制区域的宽度
	 * @param height
	 *            绘制区域的高度
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawTexture2D(Texture2D texture2D, int dx, int dy, int width, int height, int x, int y) {

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
	public void drawRect(float x, float y, float width, float height) {
		glBegin(GL11.GL_LINE_LOOP);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
	}

	/**
	 * 绘制矩形区域
	 * 
	 * @param rect
	 *            矩形区域
	 */
	public void drawRect(Rect rect) {
		this.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
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
		glEnable(GL11.GL_TEXTURE_2D);
		font.addGlyphs(str);
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		font.drawString(x, y, str, new org.newdawn.slick.Color(getColor().getArgb()));
		glDisable(GL11.GL_TEXTURE_2D);
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
	public void fillRect(float x, float y, float width, float height) {
		glBegin(GL11.GL_POLYGON);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
	}

	/**
	 * 填充矩形区域
	 * 
	 * @param rect
	 *            矩形区域
	 */
	public void fillRect(Rect rect) {
		this.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
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
	@SuppressWarnings("unchecked")
	public void setTextSize(int size) {
		if (font != null) {
			font.clearGlyphs();
		}
		this.textSize = size;
		font = new UnicodeFont(new Font("黑体", Font.PLAIN, this.getTextSize()));
		// Requires at least one effect
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
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
