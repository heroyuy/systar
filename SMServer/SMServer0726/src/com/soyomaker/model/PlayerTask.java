package com.soyomaker.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_player")
public class PlayerTask {

	@Id
	@EmbeddedId
	private PlayerTaskId id;// playerId-taskId

	private Integer step;// 已经完成的步骤数

	private boolean finished;// 是否完成（0 未完成 1已完成）

	public PlayerTask() {

	}

	public PlayerTask(int playerId, int taskId) {
		this.id = new PlayerTaskId(playerId, taskId);
		this.step = 0;
	}

	public PlayerTaskId getId() {
		return id;
	}

	public Integer getStep() {
		return step;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setId(PlayerTaskId id) {
		this.id = id;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}
