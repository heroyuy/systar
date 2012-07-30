package com.soyomaker.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.model.db.SMDataSource;
import com.soyomaker.model.db.TableProxy;
import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class Model {

	private static Model instance = new Model();

	private Map<String, TableProxy> tableProxyMap = new HashMap<String, TableProxy>();

	public static Model getInstance() {
		return instance;
	}

	private Model() {

	}

	public void config(String configFile) {
		try {
			XMLObject configObject = XMLParser.parse(new File(configFile));
			SMDataSource.getInstance().config(
					configObject.getChild("Datasource"));
			XMLObject tableProxiesObject = configObject
					.getChild("TableProxies");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TableProxy getTableProxy(String name) {
		// TODO
		return null;
	}

}
