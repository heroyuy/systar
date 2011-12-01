package com.soyomaker.data.model.enums;

public enum ActionType {

	NULL(0), ATK(1), DEFFEND(2), ITEM(3), SKILL(4), RUN(5);

	private int id;

	private ActionType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
