package com.soyomaker.emulator.util;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.emulator.app.GameConfig;
import com.soyomaker.emulator.ui.Image;

public class ImageFactory {

	private Map<String, Image> imageMap = new HashMap<String, Image>();

	private static ImageFactory instance = new ImageFactory();

	public static ImageFactory getInstance() {
		return instance;
	}

	private ImageFactory() {

	}

	public Image createImage(int width, int height) {
		return new Image(width, height);
	}

	public Image loadImage(String fileName) {
		if (!fileName.startsWith("/")) {
			fileName = "/" + fileName;
		}
		Image image = imageMap.get(fileName);
		if (image == null) {
			System.out.println(fileName);
			image = new Image(GameConfig.getInstance().getGamePath() + fileName);
			imageMap.put(fileName, image);
		}
		return image;
	}
}
