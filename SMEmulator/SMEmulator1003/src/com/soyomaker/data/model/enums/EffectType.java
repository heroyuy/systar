package com.soyomaker.data.model.enums;

public enum EffectType {
	BASIC("基本伤害值", 0);

	private int id;

	private String name;

	private EffectType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
