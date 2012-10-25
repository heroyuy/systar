package com.soyomaker.model.task;

import java.util.List;

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

	private int preId;

	private int type;

	private int npcId;

	private String applyCnd;

	private String finishCnd;

	private String reward;

	@Transient
	private List<TaskStep> steps;

	public String getApplyCnd() {
		return applyCnd;
	}

	public String getFinishCnd() {
		return finishCnd;
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

	public int getNpcId() {
		return npcId;
	}

	public int getPreId() {
		return preId;
	}

	public String getReward() {
		return reward;
	}

	public List<TaskStep> getSteps() {
		return steps;
	}

	public int getType() {
		return type;
	}

	public void setApplyCnd(String applyCnd) {
		this.applyCnd = applyCnd;
	}

	public void setFinishCnd(String finishCnd) {
		this.finishCnd = finishCnd;
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

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public void setPreId(int preId) {
		this.preId = preId;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public void setSteps(List<TaskStep> steps) {
		this.steps = steps;
	}

	public void setType(int type) {
		this.type = type;
	}
}
