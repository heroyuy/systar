package com.soyomaker.data;

public class GDataWrapper {
	private GDataType typeId;
	private Object object;

	public GDataWrapper(GDataType typeId, Object object) {
		this.typeId = typeId;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public GDataType getTypeId() {
		return typeId;
	}

	public String toString() {
		return typeId + "(" + object.toString() + ")";
	}
}
