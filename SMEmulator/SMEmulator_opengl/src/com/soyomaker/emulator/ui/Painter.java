package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.EXTFramebufferObject.GL_FRAMEBUFFER_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glBindFramebufferEXT;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import com.soyomaker.emulator.ui.font.GlyphPainter;

/**
 * 画笔
 * 
 * @author wp_g4
 */
public class Painter {

	/**
	 * 系统帧缓冲ID
	 */
	private static final int SYSTEM_FRAMEBUFFER_ID = 0;

	private static Painter systemPainter = null;

	/** default font name is "黑体" */
	public static final String DEFAULT_FONT_NAME = "黑体";

	/** default font style is PLAIN */
	public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	/** default font size is 18 */
	public static final int DEFAULT_FONT_SIZE = 18;
	/** default color is white */
	public static final Color DEFAULT_COLOR = Color.WHITE;

	/**
	 * 当前画笔
	 */
	private static Painter curPainter = null;

	public static Painter systemPainter() {
		if (systemPainter == null) {
			systemPainter = new Painter(SYSTEM_FRAMEBUFFER_ID);
		}
		return systemPainter;
	}

	/**
	 * 画笔对应的帧缓冲
	 */
	private int frameBufferID = 0;
	/**
	 * 画笔的的初始视口
	 */
	private Rect viewPort = null;

	/**
	 * 画笔的颜色
	 */
	private Color color = null;

	/**
	 * 画笔的字体
	 */
	private Font font = null;

	/**
	 * 字形绘制委托
	 */
	private GlyphPainter glyphPainter = new GlyphPainter();

	/**
	 * 初始化标识
	 */
	private boolean unInit = true;

	public Painter(int frameBufferID) {
		this.frameBufferID = frameBufferID;
		this.setTextSize(DEFAULT_FONT_SIZE);
		this.setColor(DEFAULT_COLOR);
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
	public void clipRect(float x, float y, float width, float height) {
		y = this.viewPort.getHeight() - y - height;
		// 视口
		glViewport((int) x, (int) y, (int) width, (int) height);
		// 设置投影变换
		glMatrixMode(GL_PROJECTION);
		// 重置投影矩阵
		glLoadIdentity();
		// 转换坐标系（opengl坐标原点在左下角，转换为java坐标系，原点在左上角）
		glOrtho(0, width, height, 0, 1, -1);
		// 设置模型变换
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
	public void drawLine(float x1, float y1, float x2, float y2) {
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
	 * 绘制字符串
	 * 
	 * @param str
	 *            要绘制的字符串
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawString(String str, float x, float y) {
		glyphPainter.drawString(str, x, y, font);
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
	public void drawTexture(Texture texture, float x, float y) {
		this.drawTexture(texture, 0, 0, texture.getWidth(), texture.getHeight(), x, y);
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
	public void drawTexture(Texture texture, float dx, float dy, float width, float height, float x, float y) {
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glBegin(GL_QUADS);
		float tw = texture.getWidth();
		float th = texture.getHeight();
		glTexCoord2f(dx / tw, dy / th);
		glVertex2f(x, y);
		glTexCoord2f((dx + width) / tw, dy / th);
		glVertex2f(x + width, y);
		glTexCoord2f((dx + width) / tw, (dy + height) / th);
		glVertex2f(x + width, y + height);
		glTexCoord2f(dx / tw, (dy + height) / th);
		glVertex2f(x, y + height);
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}

	/**
	 * 绘制2D纹理
	 * 
	 * @param texture
	 *            要绘制的2D纹理
	 * @param rect
	 *            绘制区域
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 */
	public void drawTexture(Texture texture, Rect rect, float x, float y) {
		this.drawTexture(texture, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), x, y);
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
		return font.getSize();
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
	public void setTextSize(int size) {
		font = new Font(DEFAULT_FONT_NAME, DEFAULT_FONT_STYLE, size);
	}

	/**
	 * 开始绘制
	 */
	public void startPaint() {
		if (curPainter == this) {
			// 已经开始
			return;
		}
		if (curPainter != null) {
			// 当前painter保存状态
			curPainter.save();
		}
		// 切换painter
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, this.frameBufferID);
		if (this.unInit) {
			// 初始化
			this.clipRect(this.viewPort.getX(), this.viewPort.getY(), this.viewPort.getWidth(),
					this.viewPort.getHeight());
			this.unInit = false;
		} else {
			// 还原状态
			this.restore();
		}
		// 设置当前painter
		curPainter = this;
	}

	/**
	 * 停止绘制
	 */
	public void stopPaint() {
		if (curPainter == systemPainter) {
			// 系统painter不能停止
			return;
		}
		if (curPainter != this) {
			// 非当前painter，不需要停止
			return;
		}
		// 切换到系统painter
		systemPainter.startPaint();
	}

	/**
	 * 获取字符串的宽度
	 * 
	 * @param str
	 *            要测量的字符串
	 * @return 字符串的宽度
	 */
	public int stringWidth(String str) {
		return GlyphPainter.stringWidth(str, font);
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

	public void setViewPort(Rect viewPort) {
		this.viewPort = viewPort;
	}

	/**
	 * 还原状态
	 */
	private void restore() {
		// (1)视口变换
		glPopAttrib();
		// (2)投影变换
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		// (2)视图变换和模型变换
		glMatrixMode(GL_MODELVIEW);
		glPopMatrix();
		// (3)颜色
		glColor3f(1.0f * this.color.getRed() / 255, 1.0f * this.color.getGreen() / 255,
				1.0f * this.color.getBlue() / 255);
	}

	/**
	 * 保存状态
	 */
	private void save() {
		// (1)视口变换
		glPushAttrib(GL_VIEWPORT_BIT);
		// (2)投影变换
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		// (2)视图变换和模型变换
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		// (3)颜色 [注：颜色保存在对象color中]
	}

}
