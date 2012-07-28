package com.soyomaker.data;

public class SMDataWrapper {
	private SMDataType dataType;
	private Object object;

	public SMDataWrapper(SMDataType typeId, Object object) {
		this.dataType = typeId;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public SMDataType getDataType() {
		return dataType;
	}

	public String toString() {
		return dataType + "(" + object.toString() + ")";
	}
}
