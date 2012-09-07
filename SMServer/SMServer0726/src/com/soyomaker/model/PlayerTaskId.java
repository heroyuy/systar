package com.soyomaker.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * <pre>
 * 联合主键
 * 1.seri
 * 2.cons
 * 3.eq and hash
 * </pre>
 * 
 * @author chenwentao
 * 
 */
@Embeddable
public class PlayerTaskId implements Serializable {

	private static final long serialVersionUID = 6499655602063588316L;

	private Integer playerId;

	private Integer taskId;

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public PlayerTaskId() {
	}

	public PlayerTaskId(Integer playerId, Integer taskId) {
		super();
		this.playerId = playerId;
		this.taskId = taskId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerTaskId other = (PlayerTaskId) obj;
		if (playerId == null) {
			if (other.playerId != null)
				return false;
		} else if (!playerId.equals(other.playerId))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

}
