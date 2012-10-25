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

	private int mapId;

	public int getId() {
		return id;
	}

	public int getMapId() {
		return mapId;
	}

	public String getName() {
		return name;
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

}
