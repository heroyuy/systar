package com.soyomaker.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

public class SQLUtil {

	private static Logger log = Logger.getLogger(SQLUtil.class);

	public static boolean execute(String psSql, List<Object> params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int r = 0;

		try {
			conn = SMDataSource.getInstance().getConnection();
			ps = conn.prepareStatement(psSql);

			if (params != null) {
				Object[] paramsObjects = params.toArray();
				for (int i = 0; i < paramsObjects.length; i++) {
					ps.setObject(i + 1, paramsObjects[i]);
				}
			}

			r = ps.executeUpdate();
		} catch (SQLException e) {
			log.debug(e);
		}
		SQLUtil.closeStatement(ps);
		SQLUtil.closeConnection(conn);
		return r == 1;
	}

	private static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
