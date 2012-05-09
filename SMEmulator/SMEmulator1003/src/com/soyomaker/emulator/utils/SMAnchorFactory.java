package com.soyomaker.emulator.utils;

import com.soyomaker.emulator.ui.Anchor;

public class SMAnchorFactory {

	private static SMAnchorFactory instance=new SMAnchorFactory();
	
	public static SMAnchorFactory getInstance() {
		return instance;
	}

	private SMAnchorFactory(){
		
	}
	
	public Anchor createAnchor(float h,float v){
		return new Anchor(h, v);
	}
	
}
