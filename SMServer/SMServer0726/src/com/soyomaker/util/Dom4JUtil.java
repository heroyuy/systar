package com.soyomaker.util;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4JUtil {
	/**
	 * 获得整数类型的属性值
	 * 
	 * @param e
	 *            元素
	 * @param attr
	 *            属性名称
	 * @param v
	 *            默认值
	 * @return
	 */
	public static int getIntAttr(Element e, String attr, int v) {
		String s = e.attributeValue(attr);
		if (s != null) {
			return LangUtil.parseInt(s);
		} else {
			return v;
		}
	}

	/**
	 * 获得XML文件的根元素
	 * 
	 * @param file
	 * @return
	 */
	public static Element getRootElementFromClasspath(String file) {
		SAXReader reader = new SAXReader();
		try {
			Document doc;
			URL url = ClassLoader.getSystemResource(file);
			if (url == null)
				return null;

			doc = reader.read(url);
			Element rootElement = doc.getRootElement();
			return rootElement;
		} catch (Exception e) {
			Logger.getLogger(Dom4JUtil.class).error(e);
		}
		return null;
	}

	public static void loadElementAttributes(Map<String, String> attrs, Element e) {
		Iterator<Attribute> it = e.attributeIterator();
		while (it.hasNext()) {
			Attribute attr = it.next();
			attrs.put(attr.getName(), attr.getValue());
		}
	}
}
