package com.soyomaker.emulator.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
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

	/* ----------------------------- 字段 ----------------------------- */
	private BufferedImage content = null;// 图片上下文

	private Painter painter = null;

	/* ----------------------------- 构造 ----------------------------- */

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
		painter = new Painter(content.getGraphics());
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
			painter = new Painter(content.getGraphics());
			if (content.getColorModel() instanceof IndexColorModel) {
				BufferedImage temp = content;
				content = new BufferedImage(temp.getWidth(), temp.getHeight(),
						BufferedImage.TYPE_INT_ARGB);
				content.getGraphics().drawImage(temp, 0, 0, null);
				painter = new Painter(content.getGraphics());
			}
		} catch (IOException ex) {
			Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * 根据指定的Image对象创建一个新的Image对象
	 * 
	 * @param image
	 *            源Image对象
	 * 
	 */
	public Image(Image image) {
		content = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		painter = new Painter(content.getGraphics());
		painter.drawImage(image, 0, 0, Painter.LT);
	}

	/* ----------------------------- 获取属性 ----------------------------- */

	/**
	 * 返回 Image 的宽度。
	 * 
	 * @return 宽度。
	 */
	public int getWidth() {
		return content.getWidth();
	}

	/**
	 * 返回 Image 的高度。
	 * 
	 * @return 高度。
	 */
	public int getHeight() {
		return content.getHeight();
	}

	/**
	 * 获取用于绘制图像的图形上下文
	 * 
	 * @return 绘制图像的图形上下文（画笔）
	 */
	public Painter getPainter() {
		return painter;
	}

	public Color getRGB(int x, int y) {
		return new Color(content.getRGB(x, y));
	}

	/* ----------------------------- 图像处理 ----------------------------- */

	/**
	 * 清除图像
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public void clear(int x, int y, int width, int height) {
		int[] rgbs = new int[width * height];
		content.setRGB(x, y, width, height, rgbs, 0, width);
	}

	/**
	 * 将图像的源区域拷贝到指定点
	 * 
	 * @param srcX
	 *            源区域x坐标
	 * @param srcY
	 *            源区域y坐标
	 * @param srcWidth
	 *            源区域宽度
	 * @param srcHeight
	 *            源区域高度
	 * @param x
	 *            指定点x坐标
	 * @param y
	 *            指定点y坐标
	 */
	public void copyArea(int srcX, int srcY, int srcWidth, int srcHeight,
			int x, int y) {
		this.getPainter().copyArea(srcX, srcY, srcWidth, srcHeight, x, y);
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
	 * @return 裁剪后的图像
	 */
	public Image clip(int x, int y, int width, int height) {
		Image res = new Image(width, height);
		res.getPainter().drawImage(this, x, y, width, height, 0, 0, Painter.LT);
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
	public Image flip(byte type) {
		int w = getWidth();
		int h = getHeight();
		Image res = new Image(w, h);
		Graphics g = res.content.getGraphics();
		switch (type) {
		case Image.HORIZONTAL:
			g.drawImage(this.content, 0, 0, w, h, w, 0, 0, h, null);
			break;
		case Image.VERTICAL:
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(this.content, 0, 0, w, h, 0, h, w, 0, null);
			break;
		default:
			throw new IllegalArgumentException("翻转类型只能是0（水平翻转）或者1（垂直翻转）");
		}
		return res;
	}

	/**
	 * 灰度处理
	 * 
	 * @param mask
	 *            掩码
	 * @return 处理后的图像
	 */
	@Deprecated
	public Image gray(int mask) {
		Image image = new Image(this);
		if (mask != 0) {
			for (int i = 0; i < getWidth(); i++) {
				for (int j = 0; j < getHeight(); j++) {
					Color color = image.getRGB(i, j);
					int a = color.getAlpha();
					int r = color.getRed();
					int g = color.getGreen();
					int b = color.getBlue();
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

					image.content.setRGB(i, j, new Color(a, r, g, b).getArgb());
				}
			}
		}
		return image;
	}

	/**
	 * 旋转图片
	 * 
	 * @param src
	 *            源图像
	 * @param angle
	 *            要旋转的度数
	 * @return 旋转后的图像
	 */
	public Image rotate(int angle) {
		// 将度数调整到 [0,360)
		if (angle < 0) {
			angle %= 360;
			angle += 360;
		} else if (angle >= 360) {
			angle %= 360;
		}
		if (angle == 0) {
			return new Image(this);
		}
		double radian = Math.toRadians(angle);
		// 计算旋转后的图片的宽高
		int w = (int) (Math.abs(this.getWidth() * Math.cos(radian)) + Math
				.abs(this.getHeight() * Math.sin(radian)));
		int h = (int) (Math.abs(this.getWidth() * Math.sin(radian)) + Math
				.abs(this.getHeight() * Math.cos(radian)));
		// 计算偏移量
		int tx = 0, ty = 0;
		if (angle > 0 && angle <= 90) {
			// (0,90]
			tx = (int) (this.getHeight() * Math.sin(radian));
			ty = 0;
		} else if (angle > 90 && angle <= 180) {
			tx = (int) (this.getHeight() * Math.cos(radian - Math.PI / 2) + this
					.getWidth() * Math.sin(radian - Math.PI / 2));
			ty = (int) (this.getHeight() * Math.sin(radian - Math.PI / 2));
		} else if (angle > 180 && angle <= 270) {
			tx = (int) (this.getWidth() * Math.cos(radian - Math.PI));
			ty = (int) (this.getWidth() * Math.sin(radian - Math.PI) + this
					.getHeight() * Math.cos(radian - Math.PI));
		} else if (angle > 270 && angle < 360) {
			tx = 0;
			ty = (int) (this.getWidth() * Math.cos(radian - Math.PI * 3 / 2));
		}

		Image image = new Image(w, h);
		Graphics2D g2d = (Graphics2D) image.content.getGraphics();
		g2d.translate(tx, ty);
		g2d.rotate(Math.toRadians(angle), 0, 0);
		g2d.drawImage(this.content, 0, 0, null);
		return image;
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
	public Image scale(int width, int height) {
		Image image = new Image(width, height);
		Graphics g = image.content.getGraphics();
		g.drawImage(this.content, 0, 0, width, height, null);
		return image;
	}

	/**
	 * 半透明处理
	 * 
	 * @param alpha
	 *            alpha值
	 * @return 半透明处理后的图像
	 */
	public Image alpha(float alpha) {
		Image image = new Image(this.getWidth(), this.getHeight());
		Graphics2D g2d = (Graphics2D) image.content.getGraphics();
		float[] scales = { 1.0f, 1.0f, 1.0f, alpha };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		g2d.drawImage(this.content, rop, 0, 0);
		return image;
	}

	/**
	 * 变色处理
	 * 
	 * @param red
	 *            红色分量
	 * @param green
	 *            绿色分量
	 * @param blue
	 *            蓝色分量
	 * @return 变色处理后的图像
	 */
	public Image tone(float red, float green, float blue) {
		if (red < 0) {
			red = 0;
		}
		if (green < 0) {
			green = 0;
		}
		if (blue < 0) {
			blue = 0;
		}
		float total = red + green + blue;
		red = red / total;
		green = green / total;
		blue = blue / total;
		Image image = new Image(this.getWidth(), this.getHeight());
		Graphics2D g2d = (Graphics2D) image.content.getGraphics();
		float[] scales = { red, green, blue, 1.0f };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		g2d.drawImage(this.content, rop, 0, 0);
		return image;
	}

	/* ----------------------------- 辅助方法 ----------------------------- */

	BufferedImage getContent() {
		return content;
	}
}
