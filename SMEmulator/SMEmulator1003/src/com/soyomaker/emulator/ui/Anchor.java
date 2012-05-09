package com.soyomaker.emulator.ui;

/**
 * 锚点，不可变对象
 * @author wp_g4
 *
 */
public class Anchor {
	
	private float h;
	private float v;
	
	public Anchor(float h,float v){
		this.h=h;
		this.v=v;
	}

	public float getH() {
		return h;
	}

	public float getV() {
		return v;
	}
}
