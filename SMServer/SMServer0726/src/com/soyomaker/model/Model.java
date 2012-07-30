package com.soyomaker.model;

import com.soyomaker.model.db.TableProxy;

public class Model {

	private static Model instance = new Model();

	public static Model getInstance() {
		return instance;
	}

	private Model() {

	}

	public void config(String configFile) {

	}

	public TableProxy getTableProxy(String name) {
		// TODO
		return null;
	}

}
