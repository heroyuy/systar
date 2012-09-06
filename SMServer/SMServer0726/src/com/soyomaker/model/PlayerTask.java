package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "task_player")
public class PlayerTask {

	private int playerId;

	private int taskId;

	private int step;

	public int getPlayerId() {
		return playerId;
	}

	public int getStep() {
		return step;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
