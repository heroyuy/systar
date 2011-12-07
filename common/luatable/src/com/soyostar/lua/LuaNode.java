package com.soyostar.lua;

public class LuaNode {

	private String key = null;

	private Object value = null;

	public LuaNode(Object value) {
		this.value = value;
	}

	public LuaNode(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		//(1) key
		if(key!=null&&!key.equals("")){
			sb.append(key+"=");
		}
		//(2) value
		sb.append(value);
		//(3) 格式
		if(value instanceof String){
			sb.insert(sb.length()-((String)value).length(), "\"");
			sb.insert(sb.length(), "\"");
		}
		return sb.toString();
	}

}
