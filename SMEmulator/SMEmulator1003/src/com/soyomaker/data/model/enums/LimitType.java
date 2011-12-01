package com.soyomaker.data.model.enums;

public enum LimitType {

	NULL(0), MENU_ONLY(1), BATTLE_ONLY(2), UNABLE(3), OTHER(4);

	private int id;

	private LimitType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
