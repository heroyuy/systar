package com.soyomaker.data.model.enums;

public enum CostType {
	SP("SP消耗", 0);

	private int id;

	private String name;

	private CostType(String name, int id) {
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
