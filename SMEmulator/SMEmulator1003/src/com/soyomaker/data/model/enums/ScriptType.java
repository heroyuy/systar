package com.soyomaker.data.model.enums;

public enum ScriptType {

	AUTO(0), TOUCH(1), KEY(2);

	private int id;

	private ScriptType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
