package com.gudigital.application;

import java.util.HashMap;
import java.util.Map;

/**
 * IBean的模板实现。子类可以直接继承这个类，或者copy其中的代码。
 * 
 * @author zhangjun
 * 
 */
public abstract class AbstractBean implements IBean {
	private BeanFactory beanFactory;
	private Map<String, String> params = new HashMap<String, String>();

	@Override
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	@Override
	public int getIntParam(String name, int defValue) {
		String s = getParam(name);
		try {
			if (s != null) {
				return Integer.parseInt(s);
			}
		} catch (Exception e) {
		}
		return defValue;
	}

	@Override
	public String getParam(String name) {
		return params.get(name);
	}

	@Override
	public Map<String, String> getParams() {
		return params;
	}

	public abstract void initialize();

	@Override
	public void setBeanFactory(BeanFactory factory) {
		beanFactory = factory;
	}

	@Override
	public void setParam(String name, String value) {
		params.put(name, value);
	}
}
