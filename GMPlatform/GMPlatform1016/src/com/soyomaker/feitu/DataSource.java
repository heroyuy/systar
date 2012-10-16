package com.soyomaker.feitu;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.dbcp.BasicDataSource;
import org.xml.sax.SAXException;

import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class DataSource {

	private static BasicDataSource bds = null;

	public static Connection getConnection() {
		Connection conn = null;
		try {
			if (bds == null) {
				bds = new BasicDataSource();
				XMLObject dbConfig = XMLParser.parse(new File("config/db.xml"));
				bds.setDriverClassName(dbConfig.getChild("driver").getValue());
				bds.setUrl(dbConfig.getChild("url").getValue());
				bds.setUsername(dbConfig.getChild("username").getValue());
				bds.setPassword(dbConfig.getChild("password").getValue());
			}
			conn = bds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
