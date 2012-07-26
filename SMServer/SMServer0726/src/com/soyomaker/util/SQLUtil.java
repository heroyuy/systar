package com.soyomaker.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Iterator;

import javax.sql.DataSource;

import com.soyomaker.data.IGUObject;
import com.soyomaker.exception.GUException;
import com.soyomaker.model.DataValue;
import com.soyomaker.model.dataSource.IGUDataSource;
import com.soyomaker.model.proxy.QueryParams;
import com.soyomaker.model.typeHelper.PropertyHelper;
import com.soyomaker.model.typeHelper.TypeHelper;

public class SQLUtil {
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static int execute(IGUDataSource ds, String sql, QueryParams params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int r = 0;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			if (params != null) {
				Object[] paramsObjects = params.toArray();
				for (int i = 0; i < paramsObjects.length; i++) {
					ps.setObject(i + 1, paramsObjects[i]);
				}
			}

			r = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatement(ps);
		closeConnection(conn);
		return r;
	}

	public static int getSQLType(Object o) throws GUException {
		if (o == null)
			return Types.NULL;
		if (o instanceof Boolean)
			return Types.BOOLEAN;
		if (o instanceof Byte)
			return Types.INTEGER;
		if (o instanceof Short)
			return Types.INTEGER;
		if (o instanceof Integer)
			return Types.INTEGER;
		if (o instanceof Long)
			return Types.BIGINT;
		if (o instanceof Float)
			return Types.FLOAT;
		if (o instanceof Double)
			return Types.DOUBLE;
		if (o instanceof String)
			return Types.VARCHAR;
		if (o instanceof IGUObject)
			return Types.BLOB;
		throw new GUException("Cann't get SQL type for object " + o);
	}

	public static String getUpdateSqlFromType(String tableName, TypeHelper typeHelper, DataValue de) {
		StringBuffer updateStr = new StringBuffer();
		updateStr.append("update " + tableName + " set ");
		Iterator<PropertyHelper> properties = typeHelper.iterator();
		while (properties.hasNext()) {
			PropertyHelper prop = properties.next();
			updateStr.append(prop.getColumnName() + " = " + de.get(prop.getColumnName()).getObject() + " ,");
		}
		updateStr.deleteCharAt(updateStr.length() - 1);
		updateStr.append(" where " + typeHelper.getKeyColumn() + " = " + de.getKey());

		return updateStr.toString();
	}

	public static void query(DataSource ds, String sql, Object[] params, IResultSetProcessor callback) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ResultSet rs = ps.executeQuery(sql);
			callback.proceResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatement(ps);
		closeConnection(conn);
	}

	public static void query(IGUDataSource ds, String sql, QueryParams params, IResultSetProcessor callback) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			if (params != null) {
				Object[] paramsObjects = params.toArray();
				for (int i = 0; i < paramsObjects.length; i++) {
					// 这里可能是错误的，应该使用QueryParams重新整理
					ps.setObject(i + 1, paramsObjects[i]);
				}
			}

			ResultSet rs = ps.executeQuery();
			callback.proceResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStatement(ps);
		closeConnection(conn);
	}

}
