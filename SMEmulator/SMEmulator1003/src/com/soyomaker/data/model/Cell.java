package com.soyomaker.data.model;

public class Cell extends Model {

	private int imageSetId;

	private int tiledIndex;

	public int getImageSetId() {
		return imageSetId;
	}

	public void setImageSetId(int imageSetId) {
		this.imageSetId = imageSetId;
	}

	public int getTiledIndex() {
		return tiledIndex;
	}

	public void setTiledIndex(int tiledIndex) {
		this.tiledIndex = tiledIndex;
	}

}
