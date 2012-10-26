package com.soyomaker.model;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "map")
public class MapData {

	/**
	 * 可通行的
	 */
	public static final int PASSABLE = 0;

	/**
	 * 不可通行的
	 */
	public static final int IMPASSABLE = 1;

	@Id
	private Integer id;

	private String name;

	private Integer width;

	private Integer height;

	private String ways;

	@Transient
	private int[][] wayMatrix;

	@Transient
	private Map<Integer, MapEntry> mapEntryMap;

	@Transient
	private Map<Integer, Npc> npcMap;

	public Integer getHeight() {
		return height;
	}

	public Integer getId() {
		return id;
	}

	public Collection<MapEntry> getMapEntryList() {
		return mapEntryMap.values();
	}

	public String getName() {
		return name;
	}

	public Collection<Npc> getNpcList() {
		return npcMap.values();
	}

	public MapEntry getMapEntry(int mapEntryId) {
		return mapEntryMap.get(mapEntryId);
	}

	public int getWay(int x, int y) {
		return wayMatrix[x][y];
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

	public void setMapEntryMap(Map<Integer, MapEntry> mapEntryMap) {
		this.mapEntryMap = mapEntryMap;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNpcMap(Map<Integer, Npc> npcMap) {
		this.npcMap = npcMap;
	}

	public void setWays(String ways) {
		this.ways = ways;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void updateWayMatrix() {
		this.wayMatrix = new int[this.width][this.height];
		String[] strs = this.ways.split(",");
		int index = 0;
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.wayMatrix[j][i] = Integer.parseInt(strs[index]);
				index++;
			}
		}
	}
}
