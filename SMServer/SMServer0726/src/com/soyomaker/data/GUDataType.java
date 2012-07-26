package com.soyomaker.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soyomaker.util.LangUtil;
import com.soyomaker.util.Log4JUtil;

public enum GUDataType {
	BOOL(0), BYTE(1), SHORT(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), STRING(7), OBJECT(8), BOOL_ARRAY(9), BYTE_ARRAY(10), SHORT_ARRAY(11), INT_ARRAY(
			12), LONG_ARRAY(13), FLOAT_ARRAY(14), DOUBLE_ARRAY(15), STRING_ARRAY(16), OBJECT_ARRAY(17);

	private static Map<GUDataType, String> typeNameMap = new HashMap<GUDataType, String>();

	private static Map<GUDataType, Object> typeDefaultMap = new HashMap<GUDataType, Object>();

	private static Map<GUDataType, Integer> typeSQLMap = new HashMap<GUDataType, Integer>();

	static {
		typeNameMap.put(BOOL, "bool");
		typeNameMap.put(BOOL_ARRAY, "bool_array");
		typeNameMap.put(BYTE, "byte");
		typeNameMap.put(BYTE_ARRAY, "byte_array");
		typeNameMap.put(DOUBLE, "double");
		typeNameMap.put(DOUBLE_ARRAY, "double_array");
		typeNameMap.put(FLOAT, "float");
		typeNameMap.put(FLOAT_ARRAY, "float_array");
		typeNameMap.put(INT, "int");
		typeNameMap.put(INT_ARRAY, "int_array");
		typeNameMap.put(LONG, "long");
		typeNameMap.put(LONG_ARRAY, "long_array");
		typeNameMap.put(OBJECT, "object");
		typeNameMap.put(OBJECT_ARRAY, "object_array");
		typeNameMap.put(SHORT, "short");
		typeNameMap.put(SHORT_ARRAY, "short_array");
		typeNameMap.put(STRING, "string");
		typeNameMap.put(STRING_ARRAY, "string_array");

		typeDefaultMap.put(BOOL, false);
		typeDefaultMap.put(BOOL_ARRAY, new ArrayList<Boolean>());
		typeDefaultMap.put(BYTE, (byte) 0);
		typeDefaultMap.put(BYTE_ARRAY, new ArrayList<Byte>());
		typeDefaultMap.put(DOUBLE, 0.0);
		typeDefaultMap.put(DOUBLE_ARRAY, new ArrayList<Double>());
		typeDefaultMap.put(FLOAT, 0.0f);
		typeDefaultMap.put(FLOAT_ARRAY, new ArrayList<Float>());
		typeDefaultMap.put(INT, 0);
		typeDefaultMap.put(INT_ARRAY, new ArrayList<Integer>());
		typeDefaultMap.put(LONG, 0l);
		typeDefaultMap.put(LONG_ARRAY, new ArrayList<Long>());
		typeDefaultMap.put(OBJECT, new GUObject());
		typeDefaultMap.put(OBJECT_ARRAY, new ArrayList<IGUObject>());
		typeDefaultMap.put(SHORT, (short) 0);
		typeDefaultMap.put(SHORT_ARRAY, new ArrayList<Short>());
		typeDefaultMap.put(STRING, "");
		typeDefaultMap.put(STRING_ARRAY, new ArrayList<String>());

		typeSQLMap.put(BOOL, Types.BOOLEAN);
		typeSQLMap.put(BOOL_ARRAY, Types.VARCHAR);
		typeSQLMap.put(BYTE, Types.INTEGER);
		typeSQLMap.put(BYTE_ARRAY, Types.VARCHAR);
		typeSQLMap.put(DOUBLE, Types.DOUBLE);
		typeSQLMap.put(DOUBLE_ARRAY, Types.VARCHAR);
		typeSQLMap.put(FLOAT, Types.FLOAT);
		typeSQLMap.put(FLOAT_ARRAY, Types.VARCHAR);
		typeSQLMap.put(INT, Types.INTEGER);
		typeSQLMap.put(INT_ARRAY, Types.VARCHAR);
		typeSQLMap.put(LONG, Types.BIGINT);
		typeSQLMap.put(LONG_ARRAY, Types.VARCHAR);
		typeSQLMap.put(OBJECT, Types.BLOB);
		typeSQLMap.put(OBJECT_ARRAY, Types.BLOB);
		typeSQLMap.put(SHORT, Types.INTEGER);
		typeSQLMap.put(SHORT_ARRAY, Types.VARBINARY);
		typeSQLMap.put(STRING, Types.VARCHAR);
		typeSQLMap.put(STRING_ARRAY, Types.BLOB);
	}

	public static GUDataType fromTypeId(int typeId) {
		for (GUDataType item : GUDataType.values()) {
			if (item.getTypeID() == typeId) {
				return item;
			}
		}
		throw new IllegalArgumentException("Unknown typeId for SFSDataType");
	}

	public static GUDataType fromTypeName(String typeName) {
		for (GUDataType type : typeNameMap.keySet()) {
			String name = typeNameMap.get(type);
			if (name != null && name.equalsIgnoreCase(typeName)) {
				return type;
			}
		}
		throw new java.lang.IllegalArgumentException("Unknown type name: " + typeName);
	}

	private int typeID;

	GUDataType(int id) {
		this.setTypeID(id);
	}

	public Object getDefault() {
		return typeDefaultMap.get(this);
	}

	public int getTypeID() {
		return typeID;
	}

	/**
	 * @param rs
	 * @param i
	 * @return
	 */
	public Object getValueFromResultSet(ResultSet rs, int i) {
		try {
			switch (this) {
			case BOOL:
				return rs.getBoolean(i);
			case BOOL_ARRAY:
				return getBoolArray(rs, i);
			case BYTE:
				return rs.getByte(i);
			case BYTE_ARRAY:
				return getByteArray(rs, i);
			case SHORT:
				return rs.getShort(i);
			case SHORT_ARRAY:
				return getShortArray(rs, i);
			case INT:
				return rs.getInt(i);
			case INT_ARRAY:
				return getIntArray(rs, i);
			case LONG:
				return rs.getLong(i);
			case LONG_ARRAY:
				return getLongArray(rs, i);
			case FLOAT:
				return rs.getFloat(i);
			case FLOAT_ARRAY:
				return getFloatArray(rs, i);
			case STRING:
				return rs.getString(i);
			case STRING_ARRAY:
				return getStrArray(rs, i);
			case DOUBLE:
				return rs.getDouble(i);
			case DOUBLE_ARRAY:
				return getDoubleArray(rs, i);
			case OBJECT:
				return rs.getObject(i);
			case OBJECT_ARRAY:
				return getObjectArray(rs, i);

			}
		} catch (SQLException e) {
			Log4JUtil.error(this, e);
		}
		return null;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String toString() {
		return typeNameMap.get(this);
	}

	private Object getBoolArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Boolean> r = new ArrayList<Boolean>();
		for (String s : ss) {
			r.add(Boolean.parseBoolean(s));
		}
		return r;
	}

	private Object getByteArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Byte> r = new ArrayList<Byte>();
		for (String s : ss) {
			r.add(Byte.parseByte(s));
		}
		return r;
	}

	private Object getDoubleArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Double> r = new ArrayList<Double>();
		for (String s : ss) {
			r.add(Double.parseDouble(s));
		}
		return r;
	}

	private Object getFloatArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Float> r = new ArrayList<Float>();
		for (String s : ss) {
			r.add(Float.parseFloat(s));
		}
		return r;
	}

	private Object getIntArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Integer> r = new ArrayList<Integer>();
		for (String s : ss) {
			r.add(Integer.parseInt(s));
		}
		return r;
	}

	private Object getLongArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Long> r = new ArrayList<Long>();
		for (String s : ss) {
			r.add(Long.parseLong(s));
		}
		return r;
	}

	private Object getObjectArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Object> r = new ArrayList<Object>();
		for (String s : ss) {
			r.add(s);
		}
		return r;
	}

	private Object getShortArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<Short> r = new ArrayList<Short>();
		for (String s : ss) {
			r.add(Short.parseShort(s));
		}
		return r;
	}

	private Object getStrArray(ResultSet rs, int i) throws SQLException {
		String v = rs.getString(i);
		String[] ss = LangUtil.getTokens(v, ",");
		List<String> r = new ArrayList<String>();
		for (String s : ss) {
			r.add(s);
		}
		return r;
	}
}
