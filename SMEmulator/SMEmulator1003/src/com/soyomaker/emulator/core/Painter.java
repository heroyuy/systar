package com.soyomaker.emulator.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
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

	private Graphics graphics = null;
	private Point point = null;
	private Rect curClip = null;

	Painter(Graphics graphics) {
		this.graphics = graphics;
		((Graphics2D) this.graphics).setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		point = new Point(0, 0);
		setTextSize(12);
	}

	void clipRect(int x, int y, int width, int height) {
		graphics.clipRect(x, y, width, height);
	}

	void clipRect(Rect rect) {
		graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(),
				rect.getHeight());
	}

	/**
	 * ������ת�����
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param anchor
	 * @return
	 */
	private int[] convert(int x, int y, int width, int height, int anchor) {
		int[] xy = { 0, 0 };
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
		if (img == null) {
			return;
		}
		int[] xy = convert(x, y, img.getWidth(), img.getHeight(), anchor);
		graphics.drawImage(img.content, xy[0], xy[1], null);
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
		graphics.drawLine(x1, y1, x2, y2);
	}

	/**
	 * 绘制椭圆的边框。得到一个圆或椭圆，它刚好能放入由 x、y、width 和 height 参数指定的矩形中。
	 * 
	 * @param x
	 *            要绘制椭圆的左上角的 x 坐标。
	 * @param y
	 *            要绘制椭圆的左上角的 y 坐标。
	 * @param width
	 *            要绘制椭圆的宽度。
	 * @param height
	 *            要绘制椭圆的高度。
	 */
	public void drawOval(int x, int y, int width, int height) {
		graphics.drawOval(x, y, width, height);
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
		graphics.drawRect(x, y, width, height);
	}

	/**
	 * 绘制圆角矩形区域
	 * 
	 * @param x
	 *            矩形区域左上角 x 坐标
	 * @param y
	 *            矩形区域左上角 y 坐标
	 * @param width
	 *            矩形区域宽度
	 * @param height
	 *            矩形区域高度
	 * @param arcSize
	 *            圆角半径
	 */
	public void drawRoundRect(int x, int y, int width, int height, int arcSize) {
		graphics.drawRoundRect(x, y, width, height, arcSize, arcSize);
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
	public void drawString(String str, int x, int y, int anchor) {
		long t = System.currentTimeMillis();
		int[] xy = convert(x, y, stringWidth(str), getTextSize(), anchor);
		graphics.drawString(str, xy[0], xy[1]
				- graphics.getFontMetrics().getDescent() + getTextSize());
		t = System.currentTimeMillis() - t;
		System.out.println("t:" + t);
	}

	/**
	 * 填充椭圆。得到一个圆或椭圆，它刚好能放入由 x、y、width 和 height 参数指定的矩形中。
	 * 
	 * @param x
	 *            要填充椭圆的左上角的 x 坐标。
	 * @param y
	 *            要填充椭圆的左上角的 y 坐标。
	 * @param width
	 *            要填充椭圆的宽度。
	 * @param height
	 *            要填充椭圆的高度。
	 */
	public void fillOval(int x, int y, int width, int height) {
		graphics.fillOval(x, y, width, height);
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
		graphics.fillRect(x, y, width, height);
	}

	/**
	 * 填充圆角矩形区域
	 * 
	 * @param x
	 *            矩形区域左上角 x 坐标
	 * @param y
	 *            矩形区域左上角 y 坐标
	 * @param width
	 *            矩形区域宽度
	 * @param height
	 *            矩形区域高度
	 * @param arcSize
	 *            圆角半径
	 */
	public void fillRoundRect(int x, int y, int width, int height, int arcSize) {
		graphics.fillRoundRect(x, y, width, height, arcSize, arcSize);
	}

	void forceClip(Rect clip) {
		graphics.setClip(clip.getX(), clip.getY(), clip.getWidth(),
				clip.getHeight());
	}

	Point getBasePoint() {
		return new Point(point);
	}

	/**
	 * 获取裁剪区域
	 * 
	 * @return 裁剪区域
	 */
	public Rect getClip() {
		Rectangle rectangle = graphics.getClipBounds();
		return new Rect(rectangle.x, rectangle.y, rectangle.width,
				rectangle.height);
	}

	/**
	 * 获取当前画笔颜色
	 * 
	 * @return 当前画笔颜色
	 */
	public int getColor() {
		return graphics.getColor().getRGB();
	}

	Rect getCurClip() {
		return curClip;
	}

	Graphics getGraphics() {
		return graphics;
	}

	/**
	 * 获取绘制文字的尺寸
	 * 
	 * @return 绘制文字的尺寸
	 */
	public int getTextSize() {
		return graphics.getFont().getSize();
	}

	void setBasePoint(int x, int y) {
		graphics.translate(x, y);
		point.setX(point.getX() + x);
		point.setY(point.getY() + y);

	}

	void setBasePoint(Point point) {
		graphics.translate(point.getX(), point.getY());
		this.point.setX(this.point.getX() + point.getX());
		this.point.setY(this.point.getY() + point.getY());
	}

	/**
	 * 设置裁剪区域
	 * 
	 * @param x
	 *            裁剪区域 x 坐标
	 * @param y
	 *            裁剪区域 y 坐标
	 * @param width
	 *            裁剪区域宽度
	 * @param height
	 *            裁剪区域高度
	 */
	public void setClip(int x, int y, int width, int height) {
		if (curClip != null) {
			forceClip(curClip);
		}
		graphics.clipRect(x, y, width, height);
	}

	/**
	 * 设置裁剪区域
	 * 
	 * @param rect
	 *            裁剪区域
	 */
	public void setClip(Rect rect) {
		if (curClip != null) {
			forceClip(curClip);
		}
		graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(),
				rect.getHeight());
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(int color) {
		java.awt.Color c = new java.awt.Color(color, false);
		graphics.setColor(c);

	}

	void setCurClip(Rect curClip) {
		this.curClip = curClip;
	}

	void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * 设置文字尺寸
	 * 
	 * @param size
	 *            绘制文字的尺寸
	 */
	public void setTextSize(int size) {
		graphics.setFont(new Font(Font.DIALOG, Font.PLAIN, size));
	}

	/**
	 * 获取字符串的宽度
	 * 
	 * @param str
	 *            要测量的字符串
	 * @return 字符串的宽度
	 */
	public int stringWidth(String str) {
		return graphics.getFontMetrics().stringWidth(str);
	}
}