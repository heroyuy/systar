package com.soyomaker.data.model.enums;

public enum ConditionType {

	ACTION_ROUND(0), ACTION_HP(1), ACTION_LEV(2), ACTION_SWITCH(3), ACTION_VARIABLE(
			4), EVENT_ROUND(0), EVENT_ENEMY(1), EVENT_ROLE(2), EVENT_SWITCH(3), EVENT_VARIABLE(
			4), REMOVE_BATTLE(0), REMOVE_DAMAGE(1), REMOVE_ROUND(2);

	private int id;

	private ConditionType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
