package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map_entry")
public class MapEntry {

	@Id
	private int id;

	private int mapId;

	private int x;

	private int y;

	private int type;

	private int targetMapId;

	private int targetX;

	private int targetY;

	public int getId() {
		return id;
	}

	public int getMapId() {
		return mapId;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
