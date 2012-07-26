package com.gudigital.application;

import java.util.Map;

public interface IBean {
	public BeanFactory getBeanFactory();

	public int getIntParam(String name, int defValue);

	public String getParam(String name);

	public Map<String, String> getParams();

	public void initialize();

	public void setBeanFactory(BeanFactory factory);

	public void setParam(String name, String value);
}
