package com.soyomaker.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "task")
public class Task {

	@Id
	private int id;

	private String name;

	private String intro;

	private int preTaskId;

	private int type;

	private String applyCondition;

	private String finishCondition;

	private String reward;

	@Transient
	private Collection<TaskStep> steps;

	public String getApplyCondition() {
		return applyCondition;
	}

	public String getFinishCondition() {
		return finishCondition;
	}

	public int getId() {
		return id;
	}

	public String getIntro() {
		return intro;
	}

	public String getName() {
		return name;
	}

	public int getPreTaskId() {
		return preTaskId;
	}

	public String getReward() {
		return reward;
	}

	public Collection<TaskStep> getSteps() {
		return steps;
	}

	public int getType() {
		return type;
	}

	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}

	public void setFinishCondition(String finishCondition) {
		this.finishCondition = finishCondition;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreTaskId(int preTaskId) {
		this.preTaskId = preTaskId;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public void setSteps(Collection<TaskStep> steps) {
		this.steps = steps;
	}

	public void setType(int type) {
		this.type = type;
	}
}
