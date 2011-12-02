package com.soyomaker.data.model.enums;

public enum ParamType {

	STRE(0), INTE(1), AGIL(2), DEXT(3), VITA(4), LUCK(5), HIT(6);

	private int id;

	private ParamType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
