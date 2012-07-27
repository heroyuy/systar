package com.soyomaker.data;

public class GDataWrapper {
	private GDataType dataType;
	private Object object;

	public GDataWrapper(GDataType typeId, Object object) {
		this.dataType = typeId;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public GDataType getDataType() {
		return dataType;
	}

	public String toString() {
		return dataType + "(" + object.toString() + ")";
	}
}
