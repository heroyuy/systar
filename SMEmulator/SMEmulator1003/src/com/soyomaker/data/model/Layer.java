package com.soyomaker.data.model;

public class Layer extends Model {

	private int deepth;

	private Cell[][] cells;

	public int getDeepth() {
		return deepth;
	}

	public void setDeepth(int deepth) {
		this.deepth = deepth;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

}
