package com.soyomaker.data.model.enums;

public enum StatusType {

	LIMITING(0), CONTINUOUS(1);

	private int id;

	private StatusType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
