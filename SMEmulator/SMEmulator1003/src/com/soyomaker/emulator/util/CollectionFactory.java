package com.soyomaker.emulator.util;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionFactory {
	private static CollectionFactory instance = new CollectionFactory();

	private CollectionFactory() {

	}

	public static CollectionFactory getInstance() {
		return instance;
	}
	
	public Collection<Integer> createIntegerArrayList(){
		return new ArrayList<Integer>();
	}

}
