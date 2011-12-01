package com.soyomaker.data.model.enums;

public enum TargetType {
	NULL(0), SELF_SINGLE(1), SELF_ALL(2), ENEMY_SINGLE(3), ENEMY_ALL(4), SELF_SINGLE_HP0(
			5), SELF_ALL_SP0(6), USER(7), OTHER(8);

	private int id;

	private TargetType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
