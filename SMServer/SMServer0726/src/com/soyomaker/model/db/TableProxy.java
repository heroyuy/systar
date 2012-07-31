package com.soyomaker.model.db;

import java.util.ArrayList;
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
		List<String> keys = new ArrayList<String>();
		keys.addAll(fieldMap.keySet());
		return this.insert(keys, data);
	}

	public boolean insert(List<String> keys, ISMObject data) {
		String psSql = "insert into " + this.getTableName() + "("
				+ TableProxy.getKeyString(keys) + ")" + " values ("
				+ TableProxy.getMarkString(keys.size()) + ")";
		List<Object> params = new ArrayList<Object>();
		for (int i = 0; i < keys.size(); i++) {
			params.add(TableProxy.getValue(keys.get(i), data));
		}
		boolean status = SQLUtil.execute(psSql, params);
		return status;
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

	private static Object getValue(String key, ISMObject obj) {
		return obj.get(key).getValue();
	}

	private static String getKeyString(List<String> keys) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			sb.append(key);
			if (i != keys.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	private static String getMarkString(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append("?");
			if (i != num - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
