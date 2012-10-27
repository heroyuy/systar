package com.soyomaker.model.task;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "task")
public class Task {

	/**
	 * 普通
	 */
	public static final int TASK_TYPE_NORMAL = 0;
	/**
	 * 主线
	 */
	public static final int TASK_TYPE_PLOTLINE = 1;
	/**
	 * 支线
	 */
	public static final int TASK_TYPE_BTANCH = 2;
	/**
	 * 日常
	 */
	public static final int TASK_TYPE_DAILY = 3;

	/**
	 * 没有前置任务标志
	 */
	public static final int TASK_TAG_NO_PRE_TASK = -1;

	@Id
	private int id;

	private String name;

	private int type;

	private int preTask;

	private int nextTask;

	private String intro;

	private int applyNpcId;

	private String applyCnd;

	private int finishNpcId;

	private String finishCnd;

	private String reward;

	@Transient
	private List<TaskStep> steps;

	public String getApplyCnd() {
		return applyCnd;
	}

	public int getApplyNpcId() {
		return applyNpcId;
	}

	public String getFinishCnd() {
		return finishCnd;
	}

	public int getFinishNpcId() {
		return finishNpcId;
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

	public int getNextTask() {
		return nextTask;
	}

	public int getPreTask() {
		return preTask;
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

	public void setApplyNpcId(int npcId) {
		this.applyNpcId = npcId;
	}

	public void setFinishCnd(String finishCnd) {
		this.finishCnd = finishCnd;
	}

	public void setFinishNpcId(int finishNpcId) {
		this.finishNpcId = finishNpcId;
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

	public void setNextTask(int nextTask) {
		this.nextTask = nextTask;
	}

	public void setPreTask(int preId) {
		this.preTask = preId;
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
