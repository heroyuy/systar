package com.soyomaker.data.model;

import java.util.HashMap;

public class Map extends Model {

	private String name;

	private String musicName;

	private String battleback;

	private String battleMusic;

	private int rowNum;

	private int colNum;

	private int cellWidth;

	private int cellHeight;

	private int imageSetNum;

	private HashMap<Integer, ImageSet> imageSets = new HashMap<Integer, ImageSet>();

	private int layerNum;

	private Layer[] layers;

	private int[][] areaId;

	private int[][] npcIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getBattleback() {
		return battleback;
	}

	public void setBattleback(String battleback) {
		this.battleback = battleback;
	}

	public String getBattleMusic() {
		return battleMusic;
	}

	public void setBattleMusic(String battleMusic) {
		this.battleMusic = battleMusic;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public int getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}

	public int getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
	}

	public int getImageSetNum() {
		return imageSetNum;
	}

	public void setImageSetNum(int imageSetNum) {
		this.imageSetNum = imageSetNum;
	}

	public ImageSet getImageSet(int id) {
		return imageSets.get(id);
	}

	public void putImageSets(ImageSet imageSet) {
		imageSets.put(imageSet.getId(), imageSet);
	}

	public int getLayerNum() {
		return layerNum;
	}

	public void setLayerNum(int layerNum) {
		this.layerNum = layerNum;
	}

	public Layer[] getLayers() {
		return layers;
	}

	public Layer getLayer(int index) {
		return layers[index];
	}

	public void setLayers(Layer[] layers) {
		this.layers = layers;
	}

	public int[][] getAreaId() {
		return areaId;
	}

	public void setAreaId(int[][] areaId) {
		this.areaId = areaId;
	}

	public int[][] getNpcIds() {
		return npcIds;
	}

	public void setNpcIds(int[][] npcIds) {
		this.npcIds = npcIds;
	}

}
