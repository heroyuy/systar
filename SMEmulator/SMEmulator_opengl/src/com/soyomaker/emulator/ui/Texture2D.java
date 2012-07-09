package com.soyomaker.emulator.ui;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.BufferedImageUtil;
import org.newdawn.slick.util.ResourceLoader;

public class Texture2D {

	private Texture texture = null;

	/**
	 * 创建空白纹理
	 * 
	 * @param width
	 *            纹理宽度
	 * @param height
	 *            纹理高度
	 */
	public Texture2D(int width, int height) {

	}

	/**
	 * 从文件中创建纹理
	 * 
	 * @param file
	 *            纹理文件
	 */
	public Texture2D(String file) {
		try {
			texture = TextureLoader.getTexture(file.substring(file.lastIndexOf(".") + 1),
					ResourceLoader.getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Texture2D(Image image, String name) {
		try {
			texture = BufferedImageUtil.getTexture(name, image.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 绑定纹理
	 */
	protected void bind() {
		texture.bind();
	}

	/**
	 * 获取纹理有效高度
	 * 
	 * @return 纹理有效高度
	 */
	public int getHeight() {
		return texture.getImageHeight();
	}

	/**
	 * 获取纹理高度
	 * 
	 * @return 纹理高度
	 */
	protected int getTextureHeight() {
		return texture.getTextureHeight();
	}

	/**
	 * 获取纹理宽度
	 * 
	 * @return 纹理宽度
	 */
	protected int getTextureWidth() {
		return texture.getTextureWidth();
	}

	/**
	 * 获取纹理有效宽度
	 * 
	 * @return 获取纹理有效宽度
	 */
	public int getWidth() {
		return texture.getImageWidth();
	}

}
