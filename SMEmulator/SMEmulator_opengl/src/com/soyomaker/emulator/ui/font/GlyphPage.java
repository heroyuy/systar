package com.soyomaker.emulator.ui.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.soyomaker.emulator.ui.Rect;
import com.soyomaker.emulator.ui.Texture;

/**
 * 字形页(Glyph的容器)
 * 
 * @author wp_g4
 * 
 */
public class GlyphPage {

	private static int WIDTH = 512;

	private static int HEIGHT = 512;

	private Font font = null;

	private Texture texture = null;

	private int fontHeight = 0;

	private int cursorX = 0;

	private int cursorY = 0;

	boolean test = false;

	/**
	 * 创建新的字形页
	 * 
	 * @param font
	 *            字体
	 */
	public GlyphPage(Font font) {
		this.font = font;
		this.fontHeight = GlyphPainter.fontHeight(this.font);
		this.texture = new Texture(WIDTH, HEIGHT);
	}

	/**
	 * 往字形页中添加字符
	 * 
	 * @param ch
	 *            待添加的字符
	 * @return 字符被添加的区域，若添加失败则返回null.
	 */
	public Rect addChar(char ch) {
		// 寻找存放字符的区域
		Rect rect = this.searchRect(ch);
		if (rect != null) {
			// 将字符绘制到字形页上
			BufferedImage charImage = new BufferedImage(GlyphPainter.charWidth(ch, this.font), this.fontHeight,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) charImage.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(this.font);
			g.setColor(Color.white);
			g.drawString(ch + "", 0, charImage.getHeight() - g.getFontMetrics().getDescent());
			this.texture.setData(Texture.imageToByteBuffer(charImage), (int) rect.getX(), (int) rect.getY(),
					(int) rect.getWidth(), (int) rect.getHeight());
			g.dispose();
		}
		return rect;
	}

	protected Texture getTexture() {
		return texture;
	}

	/**
	 * 为字符ch分配绘图区域
	 * 
	 * @param ch
	 *            字符
	 * @return 绘图区域，分配失败则返回null
	 */
	private Rect searchRect(char ch) {
		int charWidth = GlyphPainter.charWidth(ch, this.font);
		// 横向尝试
		if (this.cursorX + charWidth <= WIDTH && this.cursorY + this.fontHeight <= HEIGHT) {
			Rect rect = new Rect(this.cursorX, this.cursorY, charWidth, this.fontHeight);
			this.cursorX += charWidth;
			return rect;
		}
		// 横向尝试失败则竖向再尝试一次
		this.cursorX = 0;
		this.cursorY += this.fontHeight;
		if (this.cursorX + charWidth <= WIDTH && this.cursorY + this.fontHeight <= HEIGHT) {
			Rect rect = new Rect(this.cursorX, this.cursorY, charWidth, this.fontHeight);
			this.cursorX += charWidth;
			return rect;
		}
		// 两次尝试均失败则返回null
		return null;
	}
}
