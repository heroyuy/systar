package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Font;

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
	public static final int DEFAULT_TEXT_SIZE = 18;

	private Color color = null;

	private int textSize = 0;

	/** The fonts to draw to the screen */
	private UnicodeFont font = null;

	public Painter() {
		this.setTextSize(DEFAULT_TEXT_SIZE);
	}

	/**
	 * 裁剪区域
	 * 
	 * @param x
	 *            区域x坐标
	 * @param y
	 *            区域y坐标
	 * @param width
	 *            区域宽度
	 * @param height
	 *            区域高度
	 */
	public void clipRect(int x, int y, int width, int height) {
		// 视口
		glViewport(x, y, width, height);
		// 设置投影变换
		glMatrixMode(GL_PROJECTION);
		// 重置投影矩阵
		glLoadIdentity();
		// 转换坐标系（opengl坐标原点在左下角，转换为java坐标系，原点在右上角）
		glOrtho(x, x + width, y + height, y, 1, -1);
		// 设置投影变换
		glMatrixMode(GL_MODELVIEW);
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
		glBegin(GL_LINES);
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
		glBegin(GL_LINE_LOOP);
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
	 * 绘制字符串。TODO 应该优化,在游戏启动时加载所有游戏中会用到的字符
	 * 
	 * @param str
	 *            要绘制的字符串
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawString(String str, int x, int y) {
		glEnable(GL_TEXTURE_2D);
		font.addGlyphs(str);
		try {
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		font.drawString(x, y, str, new org.newdawn.slick.Color(getColor().getArgb()));
		glDisable(GL_TEXTURE_2D);
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
		this.drawTexture2D(texture2D, 0, 0, texture2D.getWidth(), texture2D.getHeight(), x, y);
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
		glEnable(GL_TEXTURE_2D);
		Color c = getColor();
		setColor(Color.WHITE);
		glBindTexture(GL_TEXTURE_2D, texture2D.getTextureID());
		glBegin(GL_QUADS);
		float tw = texture2D.getTextureWidth();
		float th = texture2D.getTextureHeight();
		glTexCoord2f(dx / tw, dy / th);
		glVertex2f(x, y);
		glTexCoord2f((dx + width) / tw, dy / th);
		glVertex2f(x + width, y);
		glTexCoord2f((dx + width) / tw, (dy + height) / th);
		glVertex2f(x + width, y + height);
		glTexCoord2f(dx / tw, (dy + height) / th);
		glVertex2f(x, y + height);
		glEnd();
		setColor(c);
		glDisable(GL_TEXTURE_2D);
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
		glBegin(GL_POLYGON);
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
	 * 旋转
	 * 
	 * @param angle
	 *            旋转的角度
	 * @param x
	 *            旋转原点的 x 坐标
	 * @param y
	 *            旋转原点的 y 坐标
	 */
	public void rotate(double angle, double x, double y) {
		if (!(x == 0 && y == 0)) {
			double L = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			double a1 = Math.asin(y / L);
			double a = angle * Math.PI / 180;
			double l = 2 * L * Math.sin(a / 2);
			double ox = l * Math.sin(a / 2 + a1);
			double oy = l * Math.cos(a / 2 + a1);
			this.translate(ox, -oy);
		}
		glRotated(angle, 0, 0, 1);
	}

	/**
	 * 缩放
	 * 
	 * @param scale
	 *            缩放系数
	 * @param x
	 *            缩放原点的 x 坐标
	 * @param y
	 *            缩放原点的 y 坐标
	 */
	public void scale(double scale, double x, double y) {
		this.translate((1 - scale) * x, (1 - scale) * y);
		glScaled(scale, scale, 1);
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

	/**
	 * 平移
	 * 
	 * @param tx
	 *            沿 x 轴平移的距离
	 * @param ty
	 *            沿 y 轴平移的距离
	 */
	public void translate(double tx, double ty) {
		glTranslated(tx, ty, 0);
	}
}
