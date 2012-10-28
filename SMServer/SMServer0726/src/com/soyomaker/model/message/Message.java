/**
 * 
 */
package com.soyomaker.model.message;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author chenwentao
 * 
 */
@Entity
@Table(name = "message")
public class Message {

	/**
	 * 个人聊天
	 */
	public static final int CHART = 0;

	/**
	 * 群聊
	 */
	public static final int GROUP_CHART = 1;

	/**
	 * 系统通知
	 */
	public static final int SYSTEM_NOTIFICATION = 2;

	@Id
	private Integer id;

	private String title;

	private String content;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	/**
	 * 分类
	 */
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
