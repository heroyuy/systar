package com.soyomaker.data.model.enums;

public enum AniEffectType {

	NULL(0), TARGET_FLASH(1), SCREEN_FLASH(2), TARGET_DISAPPEAR(3);

	private int id;

	private AniEffectType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
