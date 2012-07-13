package com.soyomaker.emulator.ui.font;

import org.lwjgl.opengl.GL11;

import com.soyomaker.emulator.ui.Painter;
import com.soyomaker.emulator.ui.Rect;

/**
 * 字形
 * 
 * @author wp_g4
 * 
 */
public class Glyph {

	/**
	 * 字形所在的页
	 */
	private GlyphPage glyphPage = null;

	/**
	 * 字形所在的区域
	 */
	private Rect rect = null;

	/**
	 * 显示列表ID
	 */
	private int displayListID = 0;

	public Glyph(GlyphPage glyphPage, Rect rect) {
		this.glyphPage = glyphPage;
		this.rect = rect;
		// 创建显示列表
		this.displayListID = GL11.glGenLists(1);
		GL11.glNewList(this.displayListID, GL11.GL_COMPILE);
		Painter.getInstance().drawTexture(glyphPage.getTexture(), rect, 0, 0);
		GL11.glEndList();
	}

	protected int getDisplayListID() {
		return displayListID;
	}

	protected GlyphPage getGlyphPage() {
		return glyphPage;
	}

	protected Rect getRect() {
		return rect;
	}

}
