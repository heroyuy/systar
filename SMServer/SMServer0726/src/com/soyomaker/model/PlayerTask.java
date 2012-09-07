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
	private PlayerTaskId id;
	
	private Integer step;

	public PlayerTaskId getId() {
		return id;
	}

	public void setId(PlayerTaskId id) {
		this.id = id;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
}
