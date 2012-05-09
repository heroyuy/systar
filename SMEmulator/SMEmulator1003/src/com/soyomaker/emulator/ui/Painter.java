package com.soyomaker.emulator.ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import com.soyomaker.emulator.utils.ColorFactory;

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

	private Graphics2D graphics = null;// 图形上下文
	private Point point = null;// 原点
	private Rect curClip = null;// 当前裁剪区

	/**
	 * 创建画笔
	 * 
	 * @param graphics
	 *            图形上下文
	 */
	public Painter(Graphics2D graphics) {
		this.graphics = graphics;
		((Graphics2D) this.graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		point = new Point(0, 0);
		setTextSize(16);
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
		graphics.clipRect(x, y, width, height);
	}

	/**
	 * 裁剪区域
	 * 
	 * @param rect
	 *            区域
	 */
	public void clipRect(Rect rect) {
		graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
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
	 * 将图像的源区域拷贝到指定点
	 * 
	 * @param x
	 *            源区域x坐标
	 * @param y
	 *            源区域y坐标
	 * @param width
	 *            源区域宽度
	 * @param height
	 *            源区域高度
	 * @param dx
	 *            指定点x坐标
	 * @param dy
	 *            指定点y坐标
	 */
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		Composite c = ((Graphics2D) graphics).getComposite();
		((Graphics2D) graphics).setComposite(AlphaComposite.Src);
		graphics.copyArea(x, y, width, height, dx - x, dy - y);
		((Graphics2D) graphics).setComposite(c);
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
		graphics.drawImage(img.getContent(), xy[0], xy[1], null);
	}

	/**
	 * 绘制图片的指定区域
	 * 
	 * @param img
	 *            要绘制的图片
	 * @param srcx
	 *            指定区域的x坐标
	 * @param srcy
	 *            指定区域的y坐标
	 * @param width
	 *            指定区域的宽度
	 * @param height
	 *            指定区域的高度
	 * @param x
	 *            绘制位置的 x 坐标
	 * @param y
	 *            绘制位置的 y 坐标
	 * @param anchor
	 *            锚点
	 */
	public void drawImage(Image img, int srcx, int srcy, int width, int height, int x, int y, int anchor) {
		if (img == null) {
			return;
		}
		int[] xy = convert(x, y, width, height, anchor);
		graphics.drawImage(img.getContent(), xy[0], xy[1], xy[0] + width, xy[1] + height, srcx, srcy, srcx + width,
				srcy + height, null);
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
		int[] xy = convert(x, y, stringWidth(str), getTextSize(), anchor);
		graphics.drawString(str, xy[0], xy[1] - graphics.getFontMetrics().getDescent() + getTextSize());
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

	/**
	 * 强制裁剪
	 * 
	 * @param clip
	 *            裁剪区
	 */
	public void forceClip(Rect clip) {
		graphics.setClip(clip.getX(), clip.getY(), clip.getWidth(), clip.getHeight());
	}

	/**
	 * 获取画笔的alpha值
	 * 
	 * @return 画笔的alpha值
	 */
	public float getAlpha() {
		AlphaComposite alphacomposite = (AlphaComposite) graphics.getComposite();
		float alpha = 1.0f;
		if (alphacomposite != null) {
			alpha = alphacomposite.getAlpha();
		}
		return alpha;
	}

	/**
	 * 获取基准点
	 * 
	 * @return 基准点
	 */
	public Point getBasePoint() {
		return new Point(point);
	}

	/**
	 * 获取裁剪区域
	 * 
	 * @return 裁剪区域
	 */
	public Rect getClip() {
		Rectangle rectangle = graphics.getClipBounds();
		return new Rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	/**
	 * 获取当前画笔颜色
	 * 
	 * @return 当前画笔颜色
	 */
	public Color getColor() {
		return ColorFactory.getInstance().parseInt(graphics.getColor().getRGB());
	}

	/**
	 * 获取当前裁剪区
	 * 
	 * @return 当前裁剪区
	 */
	public Rect getCurClip() {
		return curClip;
	}

	/**
	 * 获取绘制文字的尺寸
	 * 
	 * @return 绘制文字的尺寸
	 */
	public int getTextSize() {
		return graphics.getFont().getSize();
	}

	/**
	 * 设置画笔的alpha值
	 * 
	 * @param alpha
	 *            画笔的alpha值
	 */
	public void setAlpha(float alpha) {
		AlphaComposite alphacomposite = AlphaComposite.getInstance(3, (float) alpha);
		graphics.setComposite(alphacomposite);
	}

	/**
	 * 设置基准点
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 */
	public void setBasePoint(int x, int y) {
		graphics.translate(x, y);
		point.setX(point.getX() + x);
		point.setY(point.getY() + y);

	}

	/**
	 * 设置基准点
	 * 
	 * @param point
	 *            基准点
	 */
	public void setBasePoint(Point point) {
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
		graphics.clipRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(Color color) {
		java.awt.Color c = new java.awt.Color(color.getArgb(), true);
		graphics.setColor(c);

	}

	/**
	 * 设置画笔颜色
	 * 
	 * @param color
	 *            颜色
	 */
	public void setColor(String color) {
		this.setColor(ColorFactory.getInstance().parseString(color));
	}

	/**
	 * 设置当前裁剪区
	 * 
	 * @param curClip
	 *            裁剪区
	 */
	public void setCurClip(Rect curClip) {
		this.curClip = curClip;
	}

	/**
	 * 设置文字尺寸
	 * 
	 * @param size
	 *            绘制文字的尺寸
	 */
	public void setTextSize(int size) {
		graphics.setFont(new Font("黑体", Font.PLAIN, size));
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

	/**
	 * 平移
	 * 
	 * @param tx
	 *            沿 x 轴平移的距离
	 * @param ty
	 *            沿 y 轴平移的距离
	 */
	public void translate(double tx, double ty) {
		graphics.translate(tx, ty);
	}

	/**
	 * 缩放
	 * 
	 * @param scale
	 *            缩放系数
	 */
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
		graphics.translate((1 - scale) * x, (1 - scale) * y);
		graphics.scale(scale, scale);
	}

	/**
	 * 旋转
	 * 
	 * @param theta
	 *            旋转的角度，以弧度为单位
	 * @param x
	 *            旋转原点的 x 坐标
	 * @param y
	 *            旋转原点的 y 坐标
	 */
	public void rotate(double theta, double x, double y) {
		graphics.rotate(theta, x, y);
	}

	public AffineTransform getTransform() {
		return graphics.getTransform();
	}

	public void setTransform(AffineTransform affineTransform) {
		graphics.setTransform(affineTransform);
	}

	/**
	 * 错切
	 * 
	 * @param shear
	 *            错切率
	 */
	public void shear(double shear) {
		graphics.shear(shear, shear);
	}

	public void test() {
		AffineTransform aff = new AffineTransform(-0.9, 0, 0, 1, 960, 0);
		graphics.transform(aff);

	}
}
