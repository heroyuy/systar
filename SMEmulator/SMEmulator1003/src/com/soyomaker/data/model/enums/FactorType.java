package com.soyomaker.data.model.enums;

public enum FactorType {
	ATTACK("基本伤害值", 0), SP("基本伤害值", 1), AGI("基本伤害值", 2), SPEED("基本伤害值", 3), DISPERSITY(
			"基本伤害值", 4), HITRITE("基本伤害值", 5);

	private int id;

	private String name;

	private FactorType(String name, int id) {
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
