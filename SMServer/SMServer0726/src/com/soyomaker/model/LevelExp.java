package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "level_exp")
public class LevelExp {

	@Id
	private int level;

	private int exp;

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
