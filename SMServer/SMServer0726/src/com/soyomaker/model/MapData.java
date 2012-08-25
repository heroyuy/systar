package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "map")
public class MapData {

	@Id
	private Integer id;

	private String name;

	private Integer width;

	private Integer height;

	private String ways;

	public Integer getHeight() {
		return height;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getWays() {
		return ways;
	}

	public Integer getWidth() {
		return width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWays(String ways) {
		this.ways = ways;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
}
