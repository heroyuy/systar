package com.soyomaker.data.model.enums;

public enum SpeedType {

	SLOW(0), NORMAL(1), FAST(2);

	private int id;

	private SpeedType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
