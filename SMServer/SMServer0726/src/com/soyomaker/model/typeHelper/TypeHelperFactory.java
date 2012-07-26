package com.soyomaker.model.typeHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.util.Dom4JUtil;

public class TypeHelperFactory extends AbstractBean {
	private String configFile;

	private Map<String, TypeHelper> typeHelpers = new HashMap<String, TypeHelper>();

	public TypeHelper getTypeHelper(String name) {
		return typeHelpers.get(name);
	}

	@Override
	public void initialize() {
		configFile = this.getParam("config");

		loadDataHelpers(configFile);
	}

	private void loadDataHelpers(String file) {
		Element root = Dom4JUtil.getRootElementFromClasspath(file);

		@SuppressWarnings("unchecked")
		Iterator<Element> dataTypeNodesIt = root.elementIterator();
		while (dataTypeNodesIt.hasNext()) {
			TypeHelper dataHelper = TypeHelper.fromElement(dataTypeNodesIt.next());
			typeHelpers.put(dataHelper.getName(), dataHelper);
		}
	}
}
