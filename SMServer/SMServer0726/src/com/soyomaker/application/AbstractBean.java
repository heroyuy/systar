package com.soyomaker.application;

import java.util.HashMap;
import java.util.Map;

/**
 * IBean的模板实现。子类可以直接继承这个类，或者copy其中的代码。
 * 
 * @author wp_g4
 * 
 */
public abstract class AbstractBean implements IBean {

	private Map<String, String> params = new HashMap<String, String>();

	@Override
	public String getParam(String name) {
		return params.get(name);
	}

	public int getIntParam(String name, int defaultValue) {
		String param = this.getParam(name);
		int value = defaultValue;
		try {
			value = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return value;
	}

	@Override
	public Map<String, String> getParams() {
		return params;
	}

	public abstract void initialize();

	@Override
	public void putParam(String name, String value) {
		params.put(name, value);
	}
}
