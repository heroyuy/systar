package com.soyomaker.data.model.enums;

public enum EventType {

	BATTLE(0), ROUND(1), REALTIME(2);

	private int id;

	private EventType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
