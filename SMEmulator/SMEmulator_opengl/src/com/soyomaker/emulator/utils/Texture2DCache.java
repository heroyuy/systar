package com.soyomaker.emulator.utils;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.emulator.ui.Image;
import com.soyomaker.emulator.ui.Texture2D;

public class Texture2DCache {

	private Map<Object, Texture2D> texture2DCacheMap = new HashMap<Object, Texture2D>();

	private static Texture2DCache instance = new Texture2DCache();

	public static Texture2DCache getInstance() {
		return instance;
	}

	private Texture2DCache() {

	}

	public Texture2D loadTexture2D(String fileName) {
		if (!texture2DCacheMap.containsKey(fileName)) {
			texture2DCacheMap.put(fileName, new Texture2D(fileName));
		}
		return texture2DCacheMap.get(fileName);
	}

	public Texture2D loadTexture2D(Image image, String name) {
		return new Texture2D(image, name);
	}

}
