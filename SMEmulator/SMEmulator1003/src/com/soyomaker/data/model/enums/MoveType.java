package com.soyomaker.data.model.enums;

public enum MoveType {

	STILL(0), RANDOM(1), APPROACH(2);

	private int id;

	private MoveType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
