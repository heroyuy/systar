package com.soyomaker.model.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.soyomaker.util.Log4JUtil;
import com.soyomaker.util.StringUtil;
import com.soyomaker.xml.XMLObject;

public class SMDataSource {

	private static SMDataSource instance = new SMDataSource();

	public static SMDataSource getInstance() {
		return instance;
	}

	private BasicDataSource ds = new BasicDataSource();

	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			Log4JUtil.error("Cann't get connection", e);
		}
		return null;
	}

	private SMDataSource() {

	}

	public void config(XMLObject configObject) {
		ds.setDriverClassName(configObject.getAttribute("driverClassName"));
		ds.setUrl(configObject.getAttribute("url"));
		ds.setUsername(configObject.getAttribute("username"));
		ds.setPassword(configObject.getAttribute("password"));
		ds.setMaxActive(StringUtil.toInt("maxActive", ds.getMaxActive()));
		ds.setMaxIdle(StringUtil.toInt("maxIdle", ds.getMaxIdle()));
	}

}
