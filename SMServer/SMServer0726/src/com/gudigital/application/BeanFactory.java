package com.gudigital.application;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.gudigital.util.Log4JUtil;
import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

/**
 * 模仿Spring构造的BeanFactory。
 * 
 * @author Administrator
 * 
 */
public class BeanFactory {

	private Map<String, IBean> beanMap = new LinkedHashMap<String, IBean>();

	public void addBean(String name, Class<? extends IBean> c) {
		try {
			beanMap.put(name, c.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public IBean getBean(String name) {
		return beanMap.get(name);
	}

	public Set<String> getBeanNames() {
		return beanMap.keySet();
	}

	public Collection<IBean> getBeans() {
		return beanMap.values();
	}

	public void initBeansWithConfig(String file) {
		try {
			System.out.println(file);
			XMLObject xmlObject = XMLParser.parse(new File(file));
			for (XMLObject beanXMLObject : xmlObject.getChildList()) {
				String name = beanXMLObject.getName();
				if (beanMap.get(name) == null) {
					Log4JUtil.error(this, "no bean named: " + name);
					return;
				}
				IBean bean = beanMap.get(name);
				bean.setBeanFactory(this);
				for (String param : beanXMLObject.getAttributeList()) {
					bean.getParams().put(param, beanXMLObject.getAttribute(param));
				}
				beanMap.put(name, (IBean) bean);

			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (IBean bean : beanMap.values()) {
			bean.initialize();
		}
	}

}
