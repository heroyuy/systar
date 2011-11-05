package com.soyomaker.emulator.test;

import com.soyomaker.emulator.core.Image;

public class TestImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Image img = null;
		// new Image(10, 10).printRGB();
		img = new Image("game/image/tileset/001-Grassland01.png");
		img.clear(0, 0, img.getWidth(), img.getHeight());
		img.printRGB();
	}

}
