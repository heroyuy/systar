package com.soyomaker.emulator.ui.font;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

/**
 * 字库
 * 
 * @author wp_g4
 * 
 */
public class GlyphPainter {

	private Graphics gTemp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();

	public GlyphPainter() {

	}

	/**
	 * 绘制字符串
	 * 
	 * @param str
	 *            字符串
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param font
	 *            字体
	 * @param color
	 *            颜色
	 */
	public void drawString(String str, float x, float y, Font font) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			this.drawChar(ch, x, y, font);
			x += this.charWidth(ch, font);
		}
	}

	/**
	 * 绘制字符
	 * 
	 * @param ch
	 *            字符
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param font
	 *            字体
	 */
	public void drawChar(char ch, float x, float y, Font font) {
		// 由font和ch获取Glyph
		Glyph glyph = this.getGlyph(ch, font);
		// 移动坐标系
		GL11.glTranslatef(x, y, 0);
		// 执行glyph的显示列表
		GL11.glCallList(glyph.getDisplayListID());
		// 恢复坐标系
		GL11.glTranslatef(-x, -y, 0);
	}

	/**
	 * 根据字符和字体获取字形
	 * 
	 * @param ch
	 *            字符
	 * @param font
	 *            字体
	 * @return 指定字体的字符的字形
	 */
	private Glyph getGlyph(char ch, Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 测量字符串的宽度
	 * 
	 * @param str
	 *            字符串
	 * @param font
	 *            字体
	 * @return 指定字体的字符串的宽度
	 */
	public int stringWidth(String str, Font font) {
		return gTemp.getFontMetrics(font).stringWidth(str);
	}

	/**
	 * 测量字符的宽度
	 * 
	 * @param ch
	 *            字符
	 * @param font
	 *            字体
	 * @return 指定字体的字符的宽度
	 */
	public int charWidth(char ch, Font font) {
		return gTemp.getFontMetrics(font).charWidth(ch);
	}
}
