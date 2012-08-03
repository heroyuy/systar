package com.soyomaker.data;

public enum GameDataType {
	BOOL(0), BYTE(1), SHORT(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), STRING(7), OBJECT(
			8), BOOL_ARRAY(9), BYTE_ARRAY(10), SHORT_ARRAY(11), INT_ARRAY(12), LONG_ARRAY(
			13), FLOAT_ARRAY(14), DOUBLE_ARRAY(15), STRING_ARRAY(16), OBJECT_ARRAY(
			17);

	public static GameDataType fromTypeId(int type) {
		for (GameDataType dataType : GameDataType.values()) {
			if (dataType.type == type) {
				return dataType;
			}
		}
		throw new IllegalArgumentException("Unknown type for GDataType");
	}

	private int type;

	private GameDataType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String toString() {
		return "GDataType:" + this.type;
	}

}
