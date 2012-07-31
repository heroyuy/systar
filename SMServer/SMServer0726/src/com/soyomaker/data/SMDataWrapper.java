package com.soyomaker.data;

public class SMDataWrapper {
	private SMDataType type;
	private Object value;

	public SMDataWrapper(SMDataType type, Object value) {
		this.type = type;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public SMDataType getType() {
		return type;
	}

	public String toString() {
		return type + "(" + value.toString() + ")";
	}
}
