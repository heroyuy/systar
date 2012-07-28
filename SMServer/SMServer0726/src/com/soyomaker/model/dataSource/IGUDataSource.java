package com.soyomaker.model.dataSource;

import java.sql.Connection;

/**
 * 就是标准的DataSource的用法。增加一个独立接口是为了未来扩展方便。
 * 
 * @author wp_g4
 * 
 */
public interface IGUDataSource {
	public Connection getConnection();
}
