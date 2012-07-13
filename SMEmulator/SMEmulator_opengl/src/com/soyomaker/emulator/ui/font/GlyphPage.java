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

	private Texture texture = null;

	public GlyphPage(Font font) {
		// TODO Auto-generated constructor stub
	}

	protected Texture getTexture() {
		return texture;
	}

	public boolean isFull() {
		return false;
	}

	public Rect addChar(char ch) {
		// TODO Auto-generated method stub
		return null;
	}
}
