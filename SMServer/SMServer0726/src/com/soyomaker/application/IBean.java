package com.soyomaker.application;

import java.util.Map;

public interface IBean {

	public String getParam(String name);

	public int getIntParam(String name,int defaultValue);

	public Map<String, String> getParams();

	public void putParam(String name, String value);
}
