package com.soyomaker.util;

public class StringUtil {

	public static int toInt(String string, int defaultValue) {
		int value = 0;
		try {
			value = Integer.parseInt(string);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

}
