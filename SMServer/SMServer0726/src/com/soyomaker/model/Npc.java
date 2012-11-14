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

	private int col;

	private int row;

	private int targetMapId;

	private int targetCol;

	private int targetRow;

	private int face;

	private int moveType;

	private int speedLevel;

	private boolean penetrable;

	public int getFace() {
		return face;
	}

	public int getId() {
		return id;
	}

	public int getMapId() {
		return mapId;
	}

	public int getMoveType() {
		return moveType;
	}

	public String getName() {
		return name;
	}

	public int getSpeedLevel() {
		return speedLevel;
	}

	public int getTargetMapId() {
		return targetMapId;
	}

	public int getTargetCol() {
		return targetCol;
	}

	public int getTargetRow() {
		return targetRow;
	}

	public int getType() {
		return type;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public boolean isPenetrable() {
		return penetrable;
	}

	public void setFace(int face) {
		this.face = face;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public void setMoveType(int moveType) {
		this.moveType = moveType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPenetrable(boolean penetrable) {
		this.penetrable = penetrable;
	}

	public void setSpeedLevel(int speedLevel) {
		this.speedLevel = speedLevel;
	}

	public void setTargetMapId(int targetMapId) {
		this.targetMapId = targetMapId;
	}

	public void setTargetCol(int targetCol) {
		this.targetCol = targetCol;
	}

	public void setTargetRow(int targetRow) {
		this.targetRow = targetRow;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
