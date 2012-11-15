package com.soyomaker.feitu;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Table {

	private static final int COLUMN_TYPE_INT = 4;

	private static final int COLUMN_TYPE_TINYINT = -6;

	private static final int COLUMN_TYPE_VARCHAR = 12;

	private static final int COLUMN_TYPE_TEXT = -1;

	private String name = null;

	private Connection conn = null;

	private Map<String, Integer> columnTypeMap = null;

	public Table(String name, Connection conn) {
		super();
		this.name = name;
		this.conn = conn;
		try {
			columnTypeMap = new HashMap<String, Integer>();
			DatabaseMetaData dmd = conn.getMetaData();
			ResultSet rs = dmd.getColumns(null, "%", name, "%");
			while (rs.next()) {
				String fieldName = rs.getString(4);
				int fieldType = rs.getInt(5);
				String fieldTypeName = rs.getString(6);
				columnTypeMap.put(fieldName, fieldType);
				Logger.getInstance().info(
						fieldName + ":" + fieldType + ":" + fieldTypeName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 清空表
	 * 
	 * @throws SQLException
	 */
	public void clear() throws SQLException {
		PreparedStatement ps = conn.prepareStatement("truncate table " + name);
		ps.execute();
		ps.close();
	}

	public void insert(Map<String, String> valueMap) {
		String[] keys = valueMap.keySet().toArray(new String[0]);
		int valueNum = keys.length;
		if (valueNum == 0) {
			return;
		}
		StringBuffer sbName = new StringBuffer();
		StringBuffer sbValue = new StringBuffer();
		for (int i = 0; i < valueNum; i++) {
			String key = keys[i];
			sbName.append(key);
			sbValue.append("?");
			if (i < valueNum - 1) {
				sbName.append(",");
				sbValue.append(",");
			}
		}
		String sql = "INSERT INTO " + name + " (" + sbName.toString()
				+ ") VALUES (" + sbValue.toString() + ")";
		Logger.getInstance().info("sql:" + sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < valueNum; i++) {
				String key = keys[i];
				int type = columnTypeMap.get(key);
				if (type == COLUMN_TYPE_INT || type == COLUMN_TYPE_TINYINT) {
					ps.setInt(i + 1, Integer.parseInt(valueMap.get(key)));
				} else if (type == COLUMN_TYPE_VARCHAR
						|| type == COLUMN_TYPE_TEXT) {
					ps.setString(i + 1, valueMap.get(key));
				}
				Logger.getInstance().info(key + ":" + valueMap.get(key));
			}
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

}
