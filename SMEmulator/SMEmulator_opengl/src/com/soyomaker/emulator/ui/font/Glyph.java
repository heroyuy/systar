package com.soyomaker.emulator.ui.font;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

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
		float dx = rect.getX();
		float dy = rect.getY();
		float width = rect.getWidth();
		float height = rect.getHeight();
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, glyphPage.getTexture().getTextureID());
		glBegin(GL_QUADS);
		float tw = glyphPage.getTexture().getWidth();
		float th = glyphPage.getTexture().getHeight();
		glTexCoord2f(dx / tw, dy / th);
		glVertex2f(0, 0);
		glTexCoord2f((dx + width) / tw, dy / th);
		glVertex2f(width, 0);
		glTexCoord2f((dx + width) / tw, (dy + height) / th);
		glVertex2f(width, height);
		glTexCoord2f(dx / tw, (dy + height) / th);
		glVertex2f(0, height);
		glEnd();
		glDisable(GL_TEXTURE_2D);
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
