package com.soyomaker.data.model.enums;

public enum TreasureType {
	EQUIP(0), ITEM(1);

	private int id;

	private TreasureType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
