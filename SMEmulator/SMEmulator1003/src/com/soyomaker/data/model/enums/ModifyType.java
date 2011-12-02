package com.soyomaker.data.model.enums;

public enum ModifyType {

	VALUE(0), TOTAL_PERCENT(1), CUR_PERCENT(2);

	private int id;

	private ModifyType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
