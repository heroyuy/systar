package com.soyomaker.emulator.ui.font;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.soyomaker.emulator.ui.Rect;

/**
 * 字库
 * 
 * @author wp_g4
 * 
 */
public class GlyphPainter {

	/**
	 * 辅助用java画笔
	 */
	private static Graphics gTemp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics();

	/**
	 * 测量字符的宽度
	 * 
	 * @param ch
	 *            字符
	 * @param font
	 *            字体
	 * @return 指定字体的字符的宽度
	 */
	public static int charWidth(char ch, Font font) {
		return gTemp.getFontMetrics(font).charWidth(ch);
	}

	/**
	 * 获取字体的高度
	 * 
	 * @param font
	 *            字体
	 * @return 高度
	 */
	public static int fontHeight(Font font) {
		return gTemp.getFontMetrics(font).getHeight();
	}

	private static String charKey(char ch) {
		return ch + "";
	}

	private static String fontKey(Font font) {
		// name style size 唯一确定一个字体
		String separator = "|";
		String fontKey = font.getName() + separator + font.getStyle() + separator + font.getSize();
		return fontKey;
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
	public static int stringWidth(String str, Font font) {
		return gTemp.getFontMetrics(font).stringWidth(str);
	}

	/**
	 * 当前字形页map 按字体索引
	 */
	private Map<String, GlyphPage> glyphPageMap = new HashMap<String, GlyphPage>();

	/**
	 * 字库 按字体-字符 索引
	 */
	private Map<String, Map<String, Glyph>> glyphMaps = new HashMap<String, Map<String, Glyph>>();

	public GlyphPainter() {

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
			x += GlyphPainter.charWidth(ch, font);
		}
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
		String fontKey = GlyphPainter.fontKey(font);
		String charKey = GlyphPainter.charKey(ch);
		Map<String, Glyph> glyphMap = glyphMaps.get(fontKey);
		if (glyphMap == null) {
			glyphMap = new HashMap<String, Glyph>();
			glyphMaps.put(fontKey, glyphMap);
		}
		Glyph glyph = glyphMap.get(charKey);
		if (glyph == null) {
			GlyphPage glyphPage = glyphPageMap.get(fontKey);
			Rect rect = null;
			if (glyphPage == null || (rect = glyphPage.addChar(ch)) == null) {
				glyphPage = new GlyphPage(font);
				glyphPageMap.put(fontKey, glyphPage);
				rect = glyphPage.addChar(ch);
			}
			glyph = new Glyph(glyphPage, rect);
			glyphMap.put(charKey, glyph);
		}
		return glyph;
	}
}
