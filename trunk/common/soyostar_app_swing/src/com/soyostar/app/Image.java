package com.soyostar.app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * 图形图像
 * 
 * @author wp_g4
 */
public class Image {

	/**
	 * 水平翻转类型
	 */
	public static final byte HORIZONTAL = 0;
	/**
	 * 垂直翻转类型
	 */
	public static final byte VERTICAL = 1;

	BufferedImage content = null;// 图片上下文

	BufferedImage contentBackup = null;// 图片上下文的备份

	Image() {

	}

	private GraphicsPainter painter = null;

	public Image(String fileName) {
		try {
			content = ImageIO.read(new File(fileName));
		} catch (IOException ex) {
			Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
		}
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	public Image(int width, int height) {
		content = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 创建用于绘制图像的图形上下文
	 * 
	 * @return 绘制图像的图形上下文（画笔）
	 */
	public Painter getPainter() {
		if (painter == null) {
			painter = new GraphicsPainter(content.getGraphics());
		}
		return painter;
	}

	/**
	 * 返回 Image 的宽度。
	 * 
	 * @return 此Image的宽度。
	 */
	public int getWidth() {
		return content.getWidth();
	}

	/**
	 * 返回 Image 的高度。
	 * 
	 * @return 此Image的高度。
	 */
	public int getHeight() {
		return content.getHeight();
	}

	/**
	 * 裁剪图像
	 * 
	 * @param x
	 *            指定矩形区域左上角的 x 坐标
	 * @param y
	 *            指定矩形区域左上角的 y 坐标
	 * @param width
	 *            指定矩形区域的宽度
	 * @param height
	 *            指定矩形区域的高度
	 */
	public void clip(int x, int y, int width, int height) {
		content = getSubimage(content, x, y, width, height);
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	public void setAlpha(int alpha) {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				int argb = content.getRGB(i, j);
				int a = Color.getAlpha(argb);
				int r = Color.getRed(argb);
				int g = Color.getGreen(argb);
				int b = Color.getBlue(argb);
				argb = Color.getColor(a == 0 ? a : alpha, r, g, b);
				content.setRGB(i, j, argb);
			}
		}
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 旋转图片，角度只能是90的倍数，可正可负，角度为正时顺时针旋转，为负时逆时针旋转。
	 * 
	 * @param src
	 *            源图像
	 * @param angle
	 *            要旋转的度数
	 * @return 旋转后的图像
	 */
	public void rotate(int angle) {
		while (angle < 0) {
			angle += 360;
		}
		if (angle % 90 != 0) {
			throw new IllegalArgumentException("角度只能是90的整数倍");
		}
		for (int i = 0, num = angle / 90; i < num; i++) {
			rotateToCW90();
		}
	}

	/**
	 * 缩放图像
	 * 
	 * @param src
	 *            源图像
	 * @param width
	 *            缩放后的图像宽度
	 * @param height
	 *            缩放后的图像高度
	 * @return 缩放后的图像
	 */
	public void scale(int width, int height) {
		Graphics g = content.getGraphics();
		g.drawImage(content, 0, 0, width, height, null);
		g.dispose();
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 翻转图像
	 * 
	 * @param src
	 *            源图像
	 * @param type
	 *            翻转类型 HORIZONTAL（水平翻转）或者 VERTICAL（垂直翻转）
	 * @return 翻转后的图像
	 */
	public void flip(byte type) {
		int w = getWidth();
		int h = getHeight();
		Graphics g = content.getGraphics();

		switch (type) {
		case Image.HORIZONTAL:
			g.drawImage(content, 0, 0, w, h, w, 0, 0, h, null);
			g.dispose();
			break;
		case Image.VERTICAL:
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(content, 0, 0, w, h, 0, h, w, 0, null);
			g2d.dispose();
			break;
		default:
			throw new IllegalArgumentException("翻转类型只能是0（水平翻转）或者1（垂直翻转）");
		}
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	public void tone(int r, int g, int b) {
		float[] scales = { r, g, b, 1.0f };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		((Graphics2D) content.getGraphics())
				.drawImage(contentBackup, rop, 0, 0);
	}

	public void gray() {
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp colorConvert = new ColorConvertOp(cs, null);
		colorConvert.filter(content, content);
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 辅助方法，顺时针旋转图片90度
	 */
	private void rotateToCW90() {
		int w = getWidth();
		int h = getHeight();
		Graphics2D g2d = (Graphics2D) content.getGraphics();
		g2d.rotate(Math.toRadians(90), h, 0);
		g2d.drawImage(content, h, 0, w, h, null);
		g2d.dispose();
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	static BufferedImage getSubimage(BufferedImage src, int x, int y,
			int width, int height) {
		BufferedImage res = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		res.getGraphics().drawImage(src, 0, 0, width, height, x, y, x + width,
				y + height, null);
		return res;
	}
}
