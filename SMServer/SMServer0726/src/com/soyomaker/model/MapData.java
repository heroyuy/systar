package com.soyomaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "map")
public class MapData {

	@Id
	private Integer id;

	private String name;

	private Integer width;

	private Integer height;

	private String ways;

	@Transient
	private int[][] wayMatrix;

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

	public int getWay(int x, int y) {
		return wayMatrix[x][y];
	}

	public void updateWayMatrix() {
		this.wayMatrix = new int[this.width][this.height];
		String[] strs = this.ways.split(",");
		int index = 0;
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.wayMatrix[i][j] = Integer.parseInt(strs[index]);
				index++;
			}
		}
	}
}
