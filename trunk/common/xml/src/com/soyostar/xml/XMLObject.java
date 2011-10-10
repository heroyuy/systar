package com.soyostar.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * XMLObject，XML对象
 * 
 * @author wp_g4
 * 
 */
public class XMLObject {

	private String name = null;// XMLObject名

	private String value = "";// XMLObject值

	private Map<String, ArrayList<XMLObject>> sons = new HashMap<String, ArrayList<XMLObject>>();// XMLObject的子XMLObject列表

	private Map<String, String> attributes = new HashMap<String, String>();// XMLObject的属性列表

	/**
	 * 添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void addAttribute(String name, String value) {
		attributes.put(name, value);
	}

	/**
	 * 添加子XMLObject
	 * 
	 * @param name
	 *            子XMLObject名
	 * @param xmlObject
	 *            子XMLObject
	 */
	public void addSon(String name, XMLObject xmlObject) {
		if (!sons.containsKey(name)) {
			sons.put(name, new ArrayList<XMLObject>());
		}
		sons.get(name).add(xmlObject);
	}

	/**
	 * 获取属性
	 * 
	 * @param name
	 *            属性名
	 * @return 属性值
	 * @throws NoSuchAttributeException
	 *             找不到名为name的属性时
	 */
	public String getAttribute(String name) throws NoSuchAttributeException {
		if (!attributes.containsKey(name)) {
			throw new NoSuchAttributeException(
					"can't find the Attribute named \"" + name + "\"");
		}
		return attributes.get(name);
	}

	/**
	 * 获取XMLObject的第一个名为name的子XMLObject
	 * 
	 * @param name
	 *            子XMLObject名
	 * @return 子XMLObject
	 * @throws NoSuchXMLObjectException
	 *             找不到名为name的子XMLObject时
	 */
	public XMLObject getFirstXMLObject(String name)
			throws NoSuchXMLObjectException {
		if (!sons.containsKey(name) || sons.get(name).size() <= 0) {
			throw new NoSuchXMLObjectException(
					"can't find the XMLObject named \"" + name + "\"");
		}
		return sons.get(name).get(0);
	}

	/**
	 * 获取XMLObject的float值
	 * 
	 * @return XMLObject的float值
	 */
	public float getFloatValue() {
		float res = 0;
		try {
			res = Float.parseFloat(value);
		} catch (NumberFormatException e) {
			System.out.println("XMLObject[" + name + "]的值[" + value
					+ "]不能转换为float");
		}
		return res;
	}

	/**
	 * 获取XMLObject的int值
	 * 
	 * @return XMLObject的int值
	 */
	public int getIntValue() {
		int res = 0;
		try {
			res = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			System.out.println("XMLObject[" + name + "]的值[" + value
					+ "]不能转换为int");
		}
		return res;
	}

	/**
	 * 获取XMLObject的名称
	 * 
	 * @return XMLObject的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取XMLObject的String值
	 * 
	 * @return XMLObject的String值
	 */
	public String getStringValue() {
		return value;
	}

	/**
	 * 获取XMLObject的第index个名为name的子XMLObject
	 * 
	 * @param name
	 *            子XMLObject名
	 * @param index
	 *            下标
	 * @return 子XMLObject
	 * @throws NoSuchXMLObjectException
	 *             找不到第index个名为name的子XMLObject时
	 */
	public XMLObject getXMLObject(String name, int index)
			throws NoSuchXMLObjectException {
		if (!sons.containsKey(name) || sons.get(name).size() <= index) {
			throw new NoSuchXMLObjectException(
					"can't find the XMLObject named \"" + name + "\"");
		}
		return sons.get(name).get(index);
	}

	/**
	 * 获取名为name的子XMLObject列表
	 * 
	 * @param name
	 *            子XMLObject名
	 * @return 子XMLObject列表
	 * @throws NoSuchXMLObjectException
	 *             找不到名为name的子XMLObject时
	 */
	public XMLObject[] getXMLObjectArray(String name)
			throws NoSuchXMLObjectException {
		if (!sons.containsKey(name) || sons.get(name).size() <= 0) {
			throw new NoSuchXMLObjectException(
					"can't find the XMLObject named \"" + name + "\"");
		}
		return sons.get(name).toArray(new XMLObject[sons.get(name).size()]);
	}

	/**
	 * 设置XMLObject的名称
	 * 
	 * @param name
	 *            XMLObject的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置XMLObject的值
	 * 
	 * @param value
	 *            XMLObject的值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		// 拼装头尾
		String src = "<" + name + ">" + "</" + name + ">";
		// 拼装属性
		String attr = "";
		for (String name : attributes.keySet()) {
			attr += " " + name + "=" + "\"" + attributes.get(name) + "\"";
		}
		// 拼装子结点
		String son = "";
		for (String sonName : sons.keySet()) {
			ArrayList<XMLObject> al = sons.get(sonName);
			for (XMLObject xo : al) {
				son += xo.toString();
			}
		}
		// 拼装值
		String value = this.value;

		// 插入头尾
		sb.append(src);

		// 插入值
		sb.insert(name.length() + 2, value);

		// 插入属性
		sb.insert(name.length() + 1, attr);

		// 插入子结点
		sb.insert(sb.length() - name.length() - 3, son);

		return sb.toString();

	}

}
