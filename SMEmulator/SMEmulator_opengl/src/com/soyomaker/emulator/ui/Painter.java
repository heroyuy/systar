package com.soyomaker.emulator.ui;

import static org.lwjgl.opengl.EXTFramebufferObject.GL_FRAMEBUFFER_EXT;
import static org.lwjgl.opengl.EXTFramebufferObject.glBindFramebufferEXT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_VIEWPORT_BIT;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glScaled;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Font;
import java.util.Stack;

import com.soyomaker.emulator.ui.font.GlyphPainter;

/**
 * 画笔
 * 
 * @author wp_g4
 */
public class Painter {

	/**
	 * 锚点：水平左对齐，竖向顶对齐
	 */
	public static final int LT = 0;
	/**
	 * 锚点：水平左对齐，竖向居中对齐
	 */
	public static final int LV = 1;
	/**
	 * 锚点：水平左对齐，竖向底对齐
	 */
	public static final int LB = 2;
	/**
	 * 锚点：水平居中对齐，竖向顶对齐
	 */
	public static final int HT = 3;
	/**
	 * 锚点：水平居中对齐，竖向居中对齐
	 */
	public static final int HV = 4;
	/**
	 * 锚点：水平居中对齐，竖向底对齐
	 */
	public static final int HB = 5;
	/**
	 * 锚点：水平右对齐，竖向顶对齐
	 */
	public static final int RT = 6;
	/**
	 * 锚点：水平右对齐，竖向居中对齐
	 */
	public static final int RV = 7;
	/**
	 * 锚点：水平右对齐，竖向底对齐
	 */
	public static final int RB = 8;

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

	/**
	 * 画笔栈
	 */
	private static Stack<Painter> painterStack = new Stack<Painter>();

	public static Painter systemPainter() {
		if (systemPainter == null) {
			systemPainter = new Painter(SYSTEM_FRAMEBUFFER_ID);
		}
		return systemPainter;
	}

	/**
	 * 还原状态
	 */
	private static void popPainter() {
		curPainter = painterStack.pop();
		// System.out.println(curPainter + " 出栈");
		// 切换painter
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, curPainter.frameBufferID);
		// (1)视口变换
		glPopAttrib();
		// (2)投影变换
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		// (2)模型变换和视图变换
		glMatrixMode(GL_MODELVIEW);
		glPopMatrix();
		// (3)颜色
		glColor4f(curPainter.color.getRed(), curPainter.color.getGreen(), curPainter.color.getBlue(),
				curPainter.color.getAlpha());
	}

	/**
	 * 保存状态
	 */
	private static void pushPainter() {
		// System.out.println(curPainter + " 入栈");
		painterStack.push(curPainter);
		// (1)视口变换
		glPushAttrib(GL_VIEWPORT_BIT);
		// (2)投影变换
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		// (2)模型变换和视图变换
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		// (3)颜色 [注：颜色保存在对象color中]
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
	 * 画笔的alpha
	 */
	private float alpha = 1.0f;

	/**
	 * 画笔的字体
	 */
	private Font font = null;

	private float[] tint = new float[] { 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f };// 当前变色参数

	/**
	 * 字形绘制委托
	 */
	private GlyphPainter glyphPainter = new GlyphPainter();

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
		float ty = this.viewPort.getHeight() - y - height;
		// 视口
		glViewport((int) x, (int) ty, (int) width, (int) height);
		// 设置投影变换
		glMatrixMode(GL_PROJECTION);
		// 重置投影矩阵
		glLoadIdentity();
		// 转换坐标系（opengl坐标原点在左下角，转换为java坐标系，原点在左上角）
		if (this.frameBufferID == SYSTEM_FRAMEBUFFER_ID) {
			glOrtho(x, x + width, y + height, y, 1, -1);
		} else {
			glOrtho(x, x + width, y, y + height, 1, -1);
		}
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
	 * @param anchor
	 *            锚点
	 */
	public void drawString(String str, float x, float y, int anchor) {
		float[] xy = convert(x, y, stringWidth(str), getTextSize(), anchor);
		x = xy[0];
		y = xy[1];
		glyphPainter.drawString(str, x, y, font);
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
	 * @param anchor
	 *            锚点
	 */
	public void drawTexture(Texture texture, float dx, float dy, float width, float height, float x, float y, int anchor) {
		float[] xy = convert(x, y, width, height, anchor);
		x = xy[0];
		y = xy[1];
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
	 * @param texture2D
	 *            要绘制的2D纹理
	 * @param x
	 *            绘制的位置的 x 坐标
	 * @param y
	 *            绘制的位置的 y 坐标
	 * @param anchor
	 *            锚点
	 */
	public void drawTexture(Texture texture, float x, float y, int anchor) {
		this.drawTexture(texture, 0, 0, texture.getWidth(), texture.getHeight(), x, y, anchor);
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
	 * @param anchor
	 *            锚点
	 */
	public void drawTexture(Texture texture, Rect rect, float x, float y, int anchor) {
		this.drawTexture(texture, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), x, y, anchor);
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
	 * 获取画笔的alpha
	 * 
	 * @return alpha值
	 */
	public float getAlpha() {
		return alpha;
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

	public float[] getTint() {
		return new float[] { tint[0], tint[1], tint[2], tint[3], tint[4], tint[5] };
	}

	/**
	 * 还原变换
	 */
	public void popTransform() {
		glPopMatrix();
	}

	/**
	 * 保存变换
	 */
	public void pushTransform() {
		glPushMatrix();
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
	 * 缩放
	 * 
	 * @param scaleX
	 *            x方向缩放系数
	 * @param scaleY
	 *            y方向缩放系数
	 * @param x
	 *            缩放原点的x坐标
	 * @param y
	 *            缩放原点的y坐标
	 */
	public void scale(double scaleX, double scaleY, double x, double y) {
		this.translate((1 - scaleX) * x, (1 - scaleY) * y);
		glScaled(scaleX, scaleY, 1);
	}

	/**
	 * 设置画笔的alpha
	 * 
	 * @param alpha
	 *            alpha值
	 */
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(Color color) {
		this.color = color;
		glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(String argbString) {
		this.setColor(Color.colorFromARGB(argbString));
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

	public void setTint(float[] tint) {
		this.tint = tint;
	}

	public void setViewPort(Rect viewPort) {
		this.viewPort = viewPort;
	}

	/**
	 * 开始绘制
	 */
	public void startPaint() {
		// 检测是否重复启动
		if (curPainter == this) {
			System.out.println("painter已经开始");
			return;
		}
		if (curPainter != null) {
			// 当前painter入栈
			Painter.pushPainter();
		}
		curPainter = this;
		// 切换painter
		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, curPainter.frameBufferID);
		curPainter.clipRect(this.viewPort.getX(), this.viewPort.getY(), this.viewPort.getWidth(),
				this.viewPort.getHeight());
	}

	/**
	 * 停止绘制
	 */
	public void stopPaint() {
		if (this != curPainter) {
			System.out.println("非当前painter，不能执行停止操作");
			return;
		}
		Painter.popPainter();
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
	 * 变色
	 * 
	 * @param sr
	 *            red分量缩放系数
	 * @param sg
	 *            green分量缩放系数
	 * @param sb
	 *            blue分量缩放系数
	 * @param tr
	 *            red分量偏移值
	 * @param tg
	 *            green分量偏移值
	 * @param tb
	 *            blue分量偏移值
	 */
	public void tint(float sr, float sg, float sb, float tr, float tg, float tb) {
		tint[0] *= sr;
		tint[1] *= sg;
		tint[2] *= sb;
		tint[3] += tr;
		tint[4] += tg;
		tint[5] += tb;
	}

	public String toString() {
		return "painter[" + this.frameBufferID + "]";
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

	/**
	 * 坐标转换
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param anchor
	 *            锚点
	 * @return 新的坐标
	 */
	private float[] convert(float x, float y, float width, float height, int anchor) {
		float[] xy = { 0, 0 };
		switch (anchor) {
		case Painter.LT: {
			xy[0] = x;
			xy[1] = y;
		}
			break;
		case Painter.LV: {
			xy[0] = x;
			xy[1] = y - height / 2;
		}
			break;
		case Painter.LB: {
			xy[0] = x;
			xy[1] = y - height;
		}
			break;
		case Painter.HT: {
			xy[0] = x - width / 2;
			xy[1] = y;
		}
			break;
		case Painter.HV: {
			xy[0] = x - width / 2;
			xy[1] = y - height / 2;
		}
			break;
		case Painter.HB: {
			xy[0] = x - width / 2;
			xy[1] = y - height;
		}
			break;
		case Painter.RT: {

			xy[0] = x - width;
			xy[1] = y;
		}
			break;
		case Painter.RV: {
			xy[0] = x - width;
			xy[1] = y - height / 2;
		}
			break;
		case Painter.RB: {
			xy[0] = x - width;
			xy[1] = y - height;
		}
			break;
		}
		return xy;
	}

}
