package com.soyomaker.data.model.enums;

public enum EquipType {
	HELM(0), ORNAMENT(1), WEAPON(2), SHIELD(3), ARMOUR(4), BOOT(5);

	private int id;

	private EquipType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
