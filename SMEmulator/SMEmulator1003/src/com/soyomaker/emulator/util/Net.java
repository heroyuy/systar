package com.soyomaker.emulator.util;

public class Net {

	private static Net instance = new Net();

	public static Net getInstance() {
		return instance;
	}

	private Net() {

	}

}
