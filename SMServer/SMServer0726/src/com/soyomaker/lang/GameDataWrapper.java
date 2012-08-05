package com.soyomaker.lang;

public class GameDataWrapper {
	private GameDataType type;
	private Object value;

	public GameDataWrapper(GameDataType type, Object value) {
		this.type = type;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public GameDataType getType() {
		return type;
	}

	public String toString() {
		return value.toString();
	}
}
