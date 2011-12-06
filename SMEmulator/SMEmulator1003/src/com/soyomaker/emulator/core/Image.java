package com.soyomaker.emulator.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

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

	/* ----------------------------- 字段 ----------------------------- */
	BufferedImage content = null;// 图片上下文

	private BufferedImage contentBackup = null;// 图片上下文的备份

	private Painter painter = null;

	/* ----------------------------- 构造 ----------------------------- */
	/**
	 * 私有构造
	 */
	private Image() {

	}

	/**
	 * 创建指定大小的图片
	 * 
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public Image(int width, int height) {
		content = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 根据指定的文件名创建图片
	 * 
	 * @param fileName
	 *            文件名
	 */
	public Image(String fileName) {
		try {
			content = ImageIO.read(new File(fileName));
		} catch (IOException ex) {
			Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
		}
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 复制
	 * 
	 * @return 复制后的图像
	 */
	public Image getClone() {
		Image res = new Image();
		res.content = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
		res.contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
		return res;
	}

	/* ----------------------------- 获取属性 ----------------------------- */

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
	 * 创建用于绘制图像的图形上下文
	 * 
	 * @return 绘制图像的图形上下文（画笔）
	 */
	public Painter getPainter() {
		// TODO 此处有BUG，当content改变时候这里却没改变
		if (painter == null) {
			painter = new Painter(content.getGraphics());
		}
		return painter;
	}

	public int getRGB(int x, int y) {
		return content.getRGB(x, y);
	}

	/* ----------------------------- 图像处理 ----------------------------- */

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

	/**
	 * 灰度处理
	 * 
	 * @param mask
	 *            掩码
	 */
	public void gray(int mask) {
		if (mask == 0) {
			content = getSubimage(contentBackup, 0, 0,
					contentBackup.getWidth(), contentBackup.getHeight());
			return;
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				int argb = contentBackup.getRGB(i, j);
				int a = Color.getAlpha(argb);
				int r = Color.getRed(argb);
				int g = Color.getGreen(argb);
				int b = Color.getBlue(argb);
				int temp = (int) (0.299 * r + 0.587 * g + 0.114 * b);
				if (r <= mask) {
					r = temp;
				}
				if (g <= mask) {
					g = temp;
				}
				if (b <= mask) {
					b = temp;
				}

				content.setRGB(i, j, Color.getColor(a, r, g, b));
			}
		}
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
	 * 半透明处理
	 * 
	 * @param alpha
	 *            alpha值
	 */
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
	 * 清除图片上指定区域内的像素
	 * 
	 * @param x
	 *            起点 X 坐标
	 * @param y
	 *            起点 Y 坐标
	 * @param width
	 *            区域宽度
	 * @param height
	 *            区域高度
	 */
	public void clear(int x, int y, int width, int height) {
//		System.out.println("clear->x:" + x + " y:" + y + " w:" + width + " h:"
//				+ height);
		for (int i = x; i < x + width; i++) {
			for (int j = 0; j < y + height; j++) {
				content.setRGB(i, j, 0);
			}
		}
		contentBackup = getSubimage(content, 0, 0, content.getWidth(),
				content.getHeight());
	}

	/**
	 * 变色
	 * 
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void tone(int alpha, int red, int green, int blue) {
		if (alpha == 0 && red == 0 && green == 0 && blue == 0) {
			content = getSubimage(contentBackup, 0, 0,
					contentBackup.getWidth(), contentBackup.getHeight());
			return;
		}
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				int argb = contentBackup.getRGB(i, j);
				int a = Color.getAlpha(argb);
				int r = Color.getRed(argb);
				int g = Color.getGreen(argb);
				int b = Color.getBlue(argb);
				if (a != 0) {
					a += alpha;
					if (a < 0) {
						a = 0;
					} else if (a > 255) {
						a = 255;
					}
				}
				r += red;
				if (r < 0) {
					r = 0;
				} else if (r > 255) {
					r = 255;
				}
				g += green;
				if (g < 0) {
					g = 0;
				} else if (g > 255) {
					g = 255;
				}
				b += blue;
				if (b < 0) {
					b = 0;
				} else if (b > 255) {
					b = 255;
				}
				content.setRGB(i, j, Color.getColor(a, r, g, b));
			}
		}

	}

	/* ----------------------------- 辅助方法 ----------------------------- */

	private static BufferedImage getSubimage(BufferedImage src, int x, int y,
			int width, int height) {
		BufferedImage res = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		res.getGraphics().drawImage(src, 0, 0, width, height, x, y, x + width,
				y + height, null);
		return res;
	}

	public void printRGB() {
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				System.out.print("  0x"
						+ Integer.toHexString(content.getRGB(i, j)));
			}
			System.out.println();
		}
	}
}
