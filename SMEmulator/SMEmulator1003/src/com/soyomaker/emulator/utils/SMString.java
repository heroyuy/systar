package com.soyomaker.emulator.utils;

public class SMString {

	private static SMString instance = new SMString();

	public static SMString getInstance() {
		return instance;
	}

	private SMString() {

	}

	public String subString(String str, int startIndex, int endIndex) {
		return str.substring(startIndex - 1, endIndex);
	}

	public int length(String str) {
		return str.length();
	}

}
