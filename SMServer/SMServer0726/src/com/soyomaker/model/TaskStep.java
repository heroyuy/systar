package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_step")
public class TaskStep {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int taskId;

	private int mapId;

	private int npcId;

	public int getId() {
		return id;
	}

	public int getMapId() {
		return mapId;
	}

	public int getNpcId() {
		return npcId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
