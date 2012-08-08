package com.soyomaker.model;

import org.nutz.dao.entity.annotation.*;

@Table("player")
public class Player {

	@Column
	@Id
	private int id;

	@Column
	@Name
	private String name;

	@Column
	private long userId;

	@Column
	private int mapId;

	@Column
	private int x;

	@Column
	private int y;

	@Column
	private int avatar;

	@Column
	private int level;

	@Column
	private int exp;

	@Column
	private int money;

	@Column
	private int stre;

	@Column
	private int agil;

	@Column
	private int inte;

	@Column
	private int vita;

	@Column
	private int dext;

	@Column
	private int luck;

	public int getAgil() {
		return agil;
	}

	public int getAvatar() {
		return avatar;
	}

	public int getDext() {
		return dext;
	}

	public int getExp() {
		return exp;
	}

	public int getId() {
		return id;
	}

	public int getInte() {
		return inte;
	}

	public int getLevel() {
		return level;
	}

	public int getLuck() {
		return luck;
	}

	public int getMapId() {
		return mapId;
	}

	public int getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	public int getStre() {
		return stre;
	}

	public long getUserId() {
		return userId;
	}

	public int getVita() {
		return vita;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setAgil(int agil) {
		this.agil = agil;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public void setDext(int dext) {
		this.dext = dext;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInte(int inte) {
		this.inte = inte;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStre(int stre) {
		this.stre = stre;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
