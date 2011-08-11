package com.soyostar.app;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
	private BufferedImage content = null;

	private Image() {
	}

	java.awt.Image getContent() {
		return content;
	}

	private GraphicsPainter painter = null;

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
	 * 返回从指定文件获取像素数据的图像。
	 * 
	 * @param fileName
	 *            以可识别文件格式包含像素数据的文件名。
	 * @return 从指定文件获取像素数据的图像。
	 */
	public static Image createImage(String fileName) {
		Image image = new Image();
		try {
			image.content = ImageIO.read(new File(fileName));
		} catch (IOException ex) {
			Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
		}
		return image;
	}

	/**
	 * 创建指定大小的空白图像，背景透明
	 * 
	 * @param width
	 *            所创建图像的宽度
	 * @param height
	 *            所创建图像的高度
	 * @return 创建的空白图像
	 */
	public static Image createImage(int width, int height) {
		Image img = new Image();
		img.content = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		return img;
	}

	/**
	 * 返回由指定矩形区域定义的子图像。
	 * 
	 * @param src
	 *            源图像
	 * @param x
	 *            指定矩形区域左上角的 x 坐标
	 * @param y
	 *            指定矩形区域左上角的 y 坐标
	 * @param width
	 *            指定矩形区域的宽度
	 * @param height
	 *            指定矩形区域的高度
	 * @return 子图像
	 */
	public static Image copyImage(Image src, int x, int y, int width, int height) {
		Image res = new Image();
		res.content = src.content.getSubimage(x, y, width, height);
		return res;
	}

	/**
	 * 返回指定图像的半透明版本
	 * 
	 * @param src
	 *            源图片
	 * @param alpha
	 *            透明度 0-100
	 * @return 指定图像的半透明版本
	 */
	public static Image createAlphaImage(Image src, int alpha) {
		if (src == null) {
			return null;
		}
		if (alpha > 100) {
			alpha = 100;
		} else if (alpha < 0) {
			alpha = 0;
		}
		Image res = Image.createImage(src.getWidth(), src.getHeight());
		Graphics2D g = (Graphics2D) res.content.getGraphics();
		Composite alphaComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, (float) (1.0 * alpha / 100)); // 指定透明度
		g.setComposite(alphaComposite);
		g.drawImage(src.content, 0, 0, null);
		g.dispose();
		return res;
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
	public static Image rotateImage(Image src, int angle) {
		while (angle < 0) {
			angle += 360;
		}
		if (angle % 90 != 0) {
			throw new IllegalArgumentException("角度只能是90的整数倍");
		}
		Image res = null;
		for (int i = 0, num = angle / 90; i < num; i++) {
			if (res == null) {
				res = src.rotateImageToCW90();
			} else {
				res = res.rotateImageToCW90();
			}
		}
		return res;
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
	public static Image zoomImage(Image src, int width, int height) {
		Image res = createImage(width, height);
		Graphics g = res.content.getGraphics();
		g.drawImage(src.content, 0, 0, width, height, null);
		g.dispose();
		return res;
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
	public static Image flipImage(Image src, byte type) {
		int w = src.getWidth();
		int h = src.getHeight();
		Image res = createImage(w, h);
		Graphics g = res.content.getGraphics();

		switch (type) {
		case Image.HORIZONTAL:
			g.drawImage(src.content, 0, 0, w, h, w, 0, 0, h, null);
			g.dispose();
			break;
		case Image.VERTICAL:
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(src.content, 0, 0, w, h, 0, h, w, 0, null);
			g2d.dispose();
			break;
		default:
			throw new IllegalArgumentException("翻转类型只能是0（水平翻转）或者1（垂直翻转）");
		}
		return res;
	}

	/**
	 * 图片变色
	 * 
	 * @param src
	 *            源图片
	 * @param rgb
	 *            目标色值，三元色，依次为 R G B
	 * @param type
	 *            变色类型，负值为减淡，正值为加深
	 * @return 变色处理后的图片
	 */
	public static Image tone(Image src, int rgb, int type) {
		Image res = Image.copyImage(src, 0, 0, src.getWidth(), src.getHeight());
		int r = Color.getRed(rgb);
		int g = Color.getGreen(rgb);
		int b = Color.getBlue(rgb);
		for (int i = 0; i < res.getWidth(); i++) {
			for (int j = 0; j < res.getHeight(); j++) {
				int oldArgb = res.content.getRGB(i, j);
				int oldA = Color.getAlpha(oldArgb);
				int oldR = Color.getRed(oldArgb);
				int oldG = Color.getGreen(oldArgb);
				int oldB = Color.getBlue(oldArgb);
				int newR = type < 0 ? Math.max(oldR, r) : Math.min(oldR, r);
				int newG = type < 0 ? Math.max(oldG, g) : Math.min(oldG, g);
				int newB = type < 0 ? Math.max(oldB, b) : Math.min(oldB, b);
				res.content
						.setRGB(i, j, Color.getColor(oldA, newR, newG, newB));
			}
		}
		return res;
	}

	/**
	 * 辅助方法，顺时针旋转图片90度
	 */
	private Image rotateImageToCW90() {
		int w = getWidth();
		int h = getHeight();
		Image res = createImage(h, w);
		Graphics2D g2d = (Graphics2D) res.content.getGraphics();
		g2d.rotate(Math.toRadians(90), h, 0);
		g2d.drawImage(content, h, 0, w, h, null);
		g2d.dispose();
		return res;
	}
}
