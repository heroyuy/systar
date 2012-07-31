package com.soyomaker.model.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.data.ISMObject;
import com.soyomaker.data.SMDataType;

public class TableProxy {

	private static final String INT = "int";

	private static final String LONG = "long";

	private static final String FLOAT = "float";

	private static final String STRING = "string";

	private Map<String, SMDataType> fieldMap = new HashMap<String, SMDataType>();

	private String primaryKey = null;

	private String tableName = null;

	private String name = null;

	public TableProxy(String name, String tableName, String primaryKey) {
		super();
		this.primaryKey = primaryKey;
		this.tableName = tableName;
		this.name = name;
	}

	public boolean insert(ISMObject data) {
		// TODO Auto-generated method stub
		return false;
	}

	public ISMObject selectByPrimaryKey(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	public ISMObject selectByPrimaryKey(long key) {
		// TODO Auto-generated method stub
		return null;
	}

	public ISMObject selectByPrimaryKey(float key) {
		// TODO Auto-generated method stub
		return null;
	}

	public ISMObject selectByPrimaryKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void putField(String name, String type) {
		if (type.equalsIgnoreCase(INT)) {
			fieldMap.put(type, SMDataType.INT);
		} else if (type.equalsIgnoreCase(LONG)) {
			fieldMap.put(type, SMDataType.LONG);
		} else if (type.equalsIgnoreCase(FLOAT)) {
			fieldMap.put(type, SMDataType.FLOAT);
		} else if (type.equalsIgnoreCase(STRING)) {
			fieldMap.put(type, SMDataType.STRING);
		}
	}

	public List<ISMObject> select(String c) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(ISMObject data) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getValue(String key, ISMObject obj) {
		SMDataType type = fieldMap.get(key);
		String value = "";
		switch (type) {
		case INT:

			break;

		default:
			break;
		}
		return value;
	}

}
