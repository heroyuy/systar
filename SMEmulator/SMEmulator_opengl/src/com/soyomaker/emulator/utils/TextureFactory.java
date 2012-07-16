package com.soyomaker.emulator.utils;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.emulator.app.GameInfo;
import com.soyomaker.emulator.ui.Texture;

public class TextureFactory {

	private static TextureFactory instance = new TextureFactory();

	public static TextureFactory getInstance() {
		return instance;
	}

	private TextureFactory() {

	}

	private Map<String, Texture> textureMap = new HashMap<String, Texture>();

	public Texture createTexture(int width, int height) {
		return new Texture(width, height);
	}

	public Texture loadTexture(String fileName) {
		Texture texture = textureMap.get(fileName);
		if (texture == null) {
			texture = new Texture(GameInfo.getInstance().getGamePath() + fileName);
			textureMap.put(fileName, texture);
		}
		return texture;
	}

}
