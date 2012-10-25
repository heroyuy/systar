package com.soyomaker.model.task;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.soyomaker.model.Player;

@Entity
@Table(name = "task_player")
public class PlayerTask {

	@Id
	@EmbeddedId
	private PlayerTaskId id;// playerId-taskId

	private Integer step;// 已经完成的步骤数

	private boolean stepOver;// 步骤是否已经结束

	private boolean finished;// 是否完成（0 未完成 1已完成）

	@Transient
	private Task task;

	public PlayerTask(Player player, Task task) {
		this.id = new PlayerTaskId(player.getId(), task.getId());
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

	public PlayerTaskId getId() {
		return id;
	}

	public Integer getStep() {
		return step;
	}

	public Task getTask() {
		return task;
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

	public void setId(PlayerTaskId id) {
		this.id = id;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public void setStepOver(boolean stepOver) {
		this.stepOver = stepOver;
	}
}
