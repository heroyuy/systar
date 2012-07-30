package com.soyomaker.model;

import java.util.HashMap;
import java.util.Map;

import com.soyomaker.model.db.TableProxy;

public class Model {

	private static Model instance = new Model();

	private Map<String, TableProxy> tableProxyMap = new HashMap<String, TableProxy>();

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
