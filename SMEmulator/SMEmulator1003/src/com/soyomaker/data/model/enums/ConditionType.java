package com.soyomaker.data.model.enums;

public enum ConditionType {

	ROUND(0), HP(1), LEV(2), SWITCH(3), VARIABLE(4);

	private int id;

	private ConditionType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
