package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "npc")
public class Npc {

	/**
	 * 普通
	 */
	public static final int TYPE_NORMAL = 0;

	/**
	 * 传送点
	 */
	public static final int TYPE_ENTRY = 1;

	/**
	 * 普通
	 */
	public static final int STATE_NORMAL = 0;

	/**
	 * 接收任务
	 */
	public static final int STATE_APPLY_TASK = 1;

	/**
	 * 进行任务
	 */
	public static final int STATE_PROCEED_TASK = 2;

	@Id
	private int id;

	private String name;

	private int type;

	private int mapId;

	private int targetMapId;

	private int targetX;

	private int targetY;

	public int getId() {
		return id;
	}

	public int getMapId() {
		return mapId;
	}

	public String getName() {
		return name;
	}

	public int getTargetMapId() {
		return targetMapId;
	}

	public int getTargetX() {
		return targetX;
	}

	public int getTargetY() {
		return targetY;
	}

	public int getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTargetMapId(int targetMapId) {
		this.targetMapId = targetMapId;
	}

	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}

	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}

	public void setType(int type) {
		this.type = type;
	}

}
