package com.soyomaker.emulator.utils;

import com.soyomaker.emulator.core.Image;


public class ImageFactory {

	public Image createImage(int width, int height) {
		return new Image(width, height);
	}

	public Image createImage(String fileName) {
		return new Image(fileName);
	}

}
