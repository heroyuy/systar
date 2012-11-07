package com.soyomaker.model.task;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "task_step")
public class TaskStep {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int taskId;

	private int npcId;

	private String script;

	private String command;

	@Transient
	public List<TaskCommand> taskCommandList;

	public String getCommand() {
		return command;
	}

	public int getId() {
		return id;
	}

	public int getNpcId() {
		return npcId;
	}

	public String getScript() {
		return script;
	}

	public List<TaskCommand> getTaskCommandList() {
		return taskCommandList;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public void setTaskCommandList(List<TaskCommand> taskCommandList) {
		this.taskCommandList = taskCommandList;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
