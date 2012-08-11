package com.soyomaker.db;

import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.dbcp.BasicDataSource;
import org.xml.sax.SAXException;

import com.soyomaker.util.StringUtil;
import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class GameDataSource {

	private static GameDataSource instance = new GameDataSource();

	public static GameDataSource getInstance() {
		return instance;
	}

	private BasicDataSource ds = new BasicDataSource();

	private GameDataSource() {

	}

	public void config(String xmlFile) {
		try {
			XMLObject configObject = XMLParser.parse(new File(xmlFile));
			ds.setDriverClassName(configObject.getAttribute("driverClassName"));
			ds.setUrl(configObject.getAttribute("url"));
			ds.setUsername(configObject.getAttribute("username"));
			ds.setPassword(configObject.getAttribute("password"));
			ds.setMaxActive(StringUtil.toInt("maxActive", ds.getMaxActive()));
			ds.setMaxIdle(StringUtil.toInt("maxIdle", ds.getMaxIdle()));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {
		return this.ds;
	}
}
