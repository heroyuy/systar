package com.soyomaker.model.task;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <pre>
 *      PlayerTask:Task=N:1
 * </pre>
 * @author chenwentao
 */
@Entity
@Table(name = "player_task")
public class PlayerTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int playerId;// 玩家ID

	private int taskId;// 任务ID

	private int step;// 已经完成的步骤数

	private boolean stepOver;// 步骤是否已经结束

	private boolean finished;// 是否完成（0 未完成 1已完成）

	@Transient
	private Task task;

	public PlayerTask() {

	}

	public PlayerTask(int playerId, Task task) {
		this.playerId = playerId;
		this.taskId = task.getId();
		this.task = task;
	}

	/**
	 * 获取当前任务步骤
	 * 
	 * @return 当前任务步骤
	 */
	public TaskStep getCurTaskStep() {
		TaskStep ts = null;
		List<TaskStep> tsList = task.getSteps();
		if (this.step < tsList.size()) {
			ts = tsList.get(this.step);
		}
		return ts;
	}

	public int getId() {
		return id;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getStep() {
		return step;
	}

	public Task getTask() {
		return task;
	}

	public int getTaskId() {
		return taskId;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isStepOver() {
		return stepOver;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setStepOver(boolean stepOver) {
		this.stepOver = stepOver;
	}

	public void setTask(Task task) {
		// TODO 有安全隐患
		this.task = task;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
