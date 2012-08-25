package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private Long userId;

	private Integer mapId;

	private String mapName;

	private Integer x;

	private Integer y;

	private Integer avatar;

	private Integer level;

	private Integer exp;

	private Integer money;

	private Integer hp;

	private Integer sp;

	private Integer stre;

	private Integer agil;

	private Integer inte;

	private Integer vita;

	private Integer dext;

	private Integer luck;

	public Integer getAgil() {
		return agil;
	}

	public Integer getAvatar() {
		return avatar;
	}

	public Integer getDext() {
		return dext;
	}

	public Integer getExp() {
		return exp;
	}

	public Integer getHp() {
		return hp;
	}

	public Integer getId() {
		return id;
	}

	public Integer getInte() {
		return inte;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getLuck() {
		return luck;
	}

	public Integer getMapId() {
		return mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public Integer getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}

	public Integer getSp() {
		return sp;
	}

	public Integer getStre() {
		return stre;
	}

	public Long getUserId() {
		return userId;
	}

	public Integer getVita() {
		return vita;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public void setAgil(Integer agil) {
		this.agil = agil;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public void setDext(Integer dext) {
		this.dext = dext;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setLuck(Integer luck) {
		this.luck = luck;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public void setStre(Integer stre) {
		this.stre = stre;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setVita(Integer vita) {
		this.vita = vita;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}
}
