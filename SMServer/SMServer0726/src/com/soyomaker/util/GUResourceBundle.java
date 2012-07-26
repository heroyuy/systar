package com.soyomaker.util;

import java.util.ResourceBundle;

public class GUResourceBundle {
	private ResourceBundle resBundle;

	public GUResourceBundle(String res) {
		resBundle = ResourceBundle.getBundle(res);
	}

	public int getInt(String key, int def) {
		String s = resBundle.getString(key);
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return def;
		}
	}

	public String getString(String key) {
		return resBundle.getString(key);
	}

	public String[] getStringArray(String key) {
		return resBundle.getStringArray(key);
	}
}
