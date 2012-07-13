package com.soyomaker.emulator.ui.font;

import java.awt.Font;

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

	public GlyphPage(Font font) {
		this.font = font;
		this.fontHeight = GlyphPainter.fontHeight(this.font);
		this.texture = new Texture(WIDTH, HEIGHT);
	}

	public Rect addChar(char ch) {
		return null;
	}

	protected Texture getTexture() {
		return texture;
	}

	public boolean isFull() {
		return false;
	}
}
