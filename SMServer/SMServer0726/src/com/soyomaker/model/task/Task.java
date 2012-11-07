package com.soyomaker.model.task;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <pre>
 *      任务由以下构成
 * 	1、申请  2、进行任务步骤  3、完成
 * 	任务可以有N(n>0)个任务步骤
 * </pre>
 * 
 * @author chenwentao
 */

@Entity
@Table(name = "task")
public class Task {

	/**
	 * 普通
	 */
	public static final int TYPE_NORMAL = 0;
	/**
	 * 主线
	 */
	public static final int TYPE_PLOTLINE = 1;
	/**
	 * 支线
	 */
	public static final int TYPE_BTANCH = 2;
	/**
	 * 日常
	 */
	public static final int TYPE_DAILY = 3;

	/**
	 * 没有前置任务标志
	 */
	public static final int TAG_NO_PRE_TASK = -1;

	@Id
	private int id;

	private String name;

	private int type;

	private int preTask;

	private int nextTask;

	private String intro;

	private int applyNpcId;

	private String applyCnd;

	private String applyCmd;

	private int finishNpcId;

	private String finishCnd;

	private String finishCmd;

	private String reward;

	@Transient
	public List<TaskCommand> applyTaskCommandList;

	@Transient
	public List<TaskCommand> finishTaskCommandList;

	@Transient
	private List<TaskStep> steps;

	public String getApplyCmd() {
		return applyCmd;
	}

	public String getApplyCnd() {
		return applyCnd;
	}

	public int getApplyNpcId() {
		return applyNpcId;
	}

	public List<TaskCommand> getApplyTaskCommandList() {
		return applyTaskCommandList;
	}

	public String getFinishCmd() {
		return finishCmd;
	}

	public String getFinishCnd() {
		return finishCnd;
	}

	public int getFinishNpcId() {
		return finishNpcId;
	}

	public List<TaskCommand> getFinishTaskCommandList() {
		return finishTaskCommandList;
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

	public void setApplyCmd(String applyCmd) {
		this.applyCmd = applyCmd;
	}

	public void setApplyCnd(String applyCnd) {
		this.applyCnd = applyCnd;
	}

	public void setApplyNpcId(int npcId) {
		this.applyNpcId = npcId;
	}

	public void setApplyTaskCommandList(List<TaskCommand> applyTaskCommandList) {
		this.applyTaskCommandList = applyTaskCommandList;
	}

	public void setFinishCmd(String finishCmd) {
		this.finishCmd = finishCmd;
	}

	public void setFinishCnd(String finishCnd) {
		this.finishCnd = finishCnd;
	}

	public void setFinishNpcId(int finishNpcId) {
		this.finishNpcId = finishNpcId;
	}

	public void setFinishTaskCommandList(List<TaskCommand> finishTaskCommandList) {
		this.finishTaskCommandList = finishTaskCommandList;
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
