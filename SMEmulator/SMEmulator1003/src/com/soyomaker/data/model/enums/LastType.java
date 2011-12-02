package com.soyomaker.data.model.enums;

public enum LastType {

	ONCE(0), TIME(1), ROUND(2);

	private int id;

	private LastType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
