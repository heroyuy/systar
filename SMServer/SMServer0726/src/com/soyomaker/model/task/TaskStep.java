package com.soyomaker.model.task;

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

	private int npcId;

	private String script;

	private String operate;

	public int getId() {
		return id;
	}

	public int getNpcId() {
		return npcId;
	}

	public String getOperate() {
		return operate;
	}

	public String getScript() {
		return script;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
