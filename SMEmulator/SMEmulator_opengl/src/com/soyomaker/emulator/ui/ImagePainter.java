package com.soyomaker.emulator.ui;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.RescaleOp;

/**
 * 
 * @author wp_g4
 */
public class ImagePainter {

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
	private float[] tint = new float[] { 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f };// 当前变色参数

	/**
	 * 创建画笔
	 * 
	 * @param graphics
	 *            图形上下文
	 */
	public ImagePainter(Graphics2D graphics) {
		this.graphics = graphics;
		this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		setTextSize(16);
	}

	/**
	 * 根据tint参数构造RescaleOp
	 * 
	 * @return RescaleOp对象
	 */
	private RescaleOp buildRescaleOp() {
		if (Math.abs(tint[0] - 1.0f) < 1.0e-2 && Math.abs(tint[1] - 1.0f) < 1.0e-2 && Math.abs(tint[2] - 1.0f) < 1.0e-2
				&& Math.abs(tint[3] - 0.0f) < 1.0e-2 && Math.abs(tint[4] - 0.0f) < 1.0e-2
				&& Math.abs(tint[5] - 0.0f) < 1.0e-2) {
			return null;
		}
		float[] scales = new float[] { tint[0], tint[1], tint[2], 1.0f };
		float[] offsets = new float[] { tint[3], tint[4], tint[5], 1.0f };
		return new RescaleOp(scales, offsets, null);
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
		case ImagePainter.LT: {
			xy[0] = x;
			xy[1] = y;
		}
			break;
		case ImagePainter.LV: {
			xy[0] = x;
			xy[1] = y - height / 2;
		}
			break;
		case ImagePainter.LB: {
			xy[0] = x;
			xy[1] = y - height;
		}
			break;
		case ImagePainter.HT: {
			xy[0] = x - width / 2;
			xy[1] = y;
		}
			break;
		case ImagePainter.HV: {
			xy[0] = x - width / 2;
			xy[1] = y - height / 2;
		}
			break;
		case ImagePainter.HB: {
			xy[0] = x - width / 2;
			xy[1] = y - height;
		}
			break;
		case ImagePainter.RT: {

			xy[0] = x - width;
			xy[1] = y;
		}
			break;
		case ImagePainter.RV: {
			xy[0] = x - width;
			xy[1] = y - height / 2;
		}
			break;
		case ImagePainter.RB: {
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
		graphics.setComposite(AlphaComposite.Src);
		graphics.copyArea(x, y, width, height, dx - x, dy - y);
		graphics.setComposite(c);
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
		graphics.drawImage(img.getContent(), this.buildRescaleOp(), xy[0], xy[1]);
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
		graphics.drawImage(img.getContent(), x, y, x + width, y + height, srcx, srcy, srcx + width, srcy + height, null);
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
	 * 翻转
	 * 
	 * @param thetaX
	 *            x向翻转角度
	 * @param thetaY
	 *            y向翻转角度
	 * @param x
	 *            基准点x坐标
	 * @param y
	 *            基准点y坐标
	 */
	public void flip(double thetaX, double thetaY, double x, double y) {
		// 角度转换为弧度
		thetaX = Math.toRadians(thetaX);
		thetaY = Math.toRadians(thetaY);
		// x方向
		AffineTransform atx = new AffineTransform(Math.cos(thetaX), Math.sin(thetaX), 0, 1, (1 - Math.cos(thetaX)) * x,
				-Math.sin(thetaX) * x);
		graphics.transform(atx);
		// y方向
		AffineTransform aty = new AffineTransform(1, 0, Math.sin(thetaY), Math.cos(thetaY), -Math.sin(thetaY) * y,
				(1 - Math.cos(thetaY)) * y);
		graphics.transform(aty);
		double a = 0.25f;// 椭圆短半轴是长半轴的0.25倍
		double cosx2 = Math.pow(Math.cos(thetaX), 2);
		double sinx2 = Math.pow(Math.sin(thetaX), 2);
		double lx = a / Math.sqrt(a * a * cosx2 + sinx2);
		double cosy2 = Math.pow(Math.cos(thetaY), 2);
		double siny2 = Math.pow(Math.sin(thetaY), 2);
		double ly = a / Math.sqrt(a * a * cosy2 + siny2);
		graphics.translate((1 - lx) * x, (1 - ly) * y);
		graphics.scale(lx, ly);
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
	 * 获取当前画笔颜色
	 * 
	 * @return 当前画笔颜色
	 */
	public Color getColor() {
		return new Color(graphics.getColor().getRGB());
	}

	/**
	 * 返回 上下文中的当前 Composite
	 * 
	 * @return 当前 Composite
	 */
	public Composite getComposite() {
		return graphics.getComposite();
	}

	/**
	 * 获取绘制文字的尺寸
	 * 
	 * @return 绘制文字的尺寸
	 */
	public int getTextSize() {
		return graphics.getFont().getSize();
	}

	public float[] getTint() {
		return new float[] { tint[0], tint[1], tint[2], tint[3], tint[4], tint[5] };
	}

	public AffineTransform getTransform() {
		return graphics.getTransform();
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
		// 角度转换为弧度
		theta = Math.toRadians(theta);
		graphics.rotate(theta, x, y);
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
		graphics.translate((1 - scale) * x, (1 - scale) * y);
		graphics.scale(scale, scale);
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
		this.setColor(new Color(color));
	}

	/**
	 * 为 上下文设置 Composite。
	 * 
	 * @param composite
	 *            用于呈现的 Composite 对象
	 */
	public void setComposite(Composite composite) {
		graphics.setComposite(composite);
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

	public void setTint(float[] tint) {
		this.tint = tint;
	}

	public void setTransform(AffineTransform affineTransform) {
		graphics.setTransform(affineTransform);
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
	 * @return RescaleOp对象
	 */
	public void tint(float sr, float sg, float sb, float tr, float tg, float tb) {
		tint[0] *= sr;
		tint[1] *= sg;
		tint[2] *= sb;
		tint[3] += tr;
		tint[4] += tg;
		tint[5] += tb;
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

}
