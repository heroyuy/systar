package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	public static final int TYPE_WEAPON = 0;// 武器

	public static final int TYPE_HELM = 1;// 头盔

	public static final int TYPE_SHOE = 2;// 鞋

	public static final int TYPE_JEWELRY = 3;// 饰品

	public static final int TYPE_ARMOR = 4;// 防弹衣

	public static final int TYPE_NORMAL = 5;// 普通物品

	public static final int TYPE_RECOVER = 6;// 恢复品

	@Id
	private int id;

	private String name;

	private String intro;

	private int atk;

	private int def;

	private int hit;

	private int flee;

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getFlee() {
		return flee;
	}

	public int getHit() {
		return hit;
	}

	public int getId() {
		return id;
	}

	public String getIntro() {
		return intro;
	}

	public String getName() {
		return name;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setFlee(int flee) {
		this.flee = flee;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setName(String name) {
		this.name = name;
	}

}
