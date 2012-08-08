package com.soyomaker.model;

public class Model {

	private static Model instance = new Model();

	public static Model getInstance() {
		return instance;
	}

	private Model() {

	}

	public void config(String configFile) {

	}

}
