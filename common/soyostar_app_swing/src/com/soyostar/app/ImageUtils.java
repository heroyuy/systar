package com.soyostar.app;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;

public class ImageUtils {

	private ImageUtils() {

	}

	public static Image getSubImage(Image src, int x, int y, int width,
			int height) {
		Image res = new Image();
		res.content = Image.getSubimage(src.content, x, y, width, height);
		return res;
	}

	public static Image createAlphaImage(Image src, int alpha) {
		Image res = new Image(src.getWidth(), src.getHeight());
		Graphics2D g = (Graphics2D) res.content.getGraphics();
		Composite alphaComposite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, (float) (1.0 * alpha / 255)); // 指定透明度
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
				res = rotateImageToCW90(src);
			} else {
				res = rotateImageToCW90(src);
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
	public static Image getScaledImage(Image src, int width, int height) {
		Image res = new Image(width, height);
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
		Image res = new Image(w, h);
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

	public static Image getTonedImage(Image src, int r, int g, int b) {
		Image res = new Image(src.getWidth(), src.getHeight());
		float[] scales = { r, g, b, 1.0f };
		float[] offsets = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		((Graphics2D) res.content.getGraphics()).drawImage(src.content, rop, 0,
				0);
		return res;
	}

	public static Image getGrayedImage(Image src) {
		Image res = new Image(src.getWidth(), src.getHeight());
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp colorConvert = new ColorConvertOp(cs, null);
		colorConvert.filter(src.content, res.content);
		return res;
	}

	/**
	 * 辅助方法，顺时针旋转图片90度
	 */
	private static Image rotateImageToCW90(Image src) {
		int w = src.getWidth();
		int h = src.getHeight();
		Image res = new Image(h, w);
		Graphics2D g2d = (Graphics2D) res.content.getGraphics();
		g2d.rotate(Math.toRadians(90), h, 0);
		g2d.drawImage(src.content, h, 0, w, h, null);
		g2d.dispose();
		return res;
	}

}
