package com.soyomaker.emulator.utils;

import com.soyomaker.emulator.app.GameInfo;
import com.soyomaker.emulator.ui.Texture2D;

public class Texture2DFactory {

	private static Texture2DFactory instance = new Texture2DFactory();

	public static Texture2DFactory getInstance() {
		return instance;
	}

	private Texture2DFactory() {

	}

	public Texture2D createTexture(int width, int height) {
		return new Texture2D(width, height);
	}

	public Texture2D loadTexture(String fileName) {
		return new Texture2D(GameInfo.getInstance().getGamePath() + fileName);
	}

}
