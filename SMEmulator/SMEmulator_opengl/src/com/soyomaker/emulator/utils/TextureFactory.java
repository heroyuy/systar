package com.soyomaker.emulator.utils;

import com.soyomaker.emulator.app.GameInfo;
import com.soyomaker.emulator.ui.Texture;

public class TextureFactory {

	private static TextureFactory instance = new TextureFactory();

	public static TextureFactory getInstance() {
		return instance;
	}

	private TextureFactory() {

	}

	public Texture createTexture(int width, int height) {
		return new Texture(width, height);
	}

	public Texture loadTexture(String fileName) {
		return new Texture(GameInfo.getInstance().getGamePath() + fileName);
	}

}
