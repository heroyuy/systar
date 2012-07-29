package com.soyomaker.model.dataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.soyomaker.common.AbstractBean;
import com.soyomaker.util.Log4JUtil;

public class GUDataSource extends AbstractBean implements IGUDataSource {
	private BasicDataSource ds = new BasicDataSource();

	@Override
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			Log4JUtil.error("Cann't get connection", e);
		}
		return null;
	}

	@Override
	public void initialize() {
		ds.setDriverClassName(getParam("driverClassName"));
		ds.setUrl(getParam("url"));
		ds.setUsername(getParam("username"));
		ds.setPassword(getParam("password"));

		ds.setMaxActive(getIntParam("maxActive", ds.getMaxActive()));
		ds.setMaxIdle(getIntParam("maxIdle", ds.getMaxIdle()));
	}

}
