package com.soyomaker.core;

public class GUDataWrapper {
	private GUDataType typeId;
	private Object object;

	public GUDataWrapper(GUDataType typeId, Object object) {
		this.typeId = typeId;
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public GUDataType getTypeId() {
		return typeId;
	}

	public String toString() {
		return typeId + "(" + object.toString() + ")";
	}
}
