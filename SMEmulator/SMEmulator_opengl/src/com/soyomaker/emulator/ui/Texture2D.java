package com.soyomaker.emulator.ui;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Texture2D {

	private Texture texture = null;

	public Texture2D(String file) {
		try {
			texture = TextureLoader.getTexture(file.substring(file.lastIndexOf(".") + 1),
					ResourceLoader.getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bind() {
		texture.bind();
	}

	public int getTextureWidth() {
		return texture.getTextureWidth();
	}

	public int getTextureHeight() {
		return texture.getTextureHeight();
	}

	public int getImageWidth() {
		return texture.getImageWidth();
	}

	public int getImageHeight() {
		return texture.getImageHeight();
	}

}
