package com.soyomaker.common;


public interface IBean {

	public String getParam(String name);

	public int getIntParam(String name, int defaultValue);

	public void putParam(String name, String value);

	public void initialize();
}
