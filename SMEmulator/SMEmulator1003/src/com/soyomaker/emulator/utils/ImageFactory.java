package com.soyomaker.emulator.utils;

import com.soyomaker.emulator.ui.Image;

public class ImageFactory {

	private static ImageFactory instance = new ImageFactory();

	public static ImageFactory getInstance() {
		return instance;
	}

	private ImageFactory() {

	}

	public Image createImage(int width, int height) {
		return new Image(width, height);
	}

	public Image createImage(String fileName) {
		return new Image(fileName);
	}

}
