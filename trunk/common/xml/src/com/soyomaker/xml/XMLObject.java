package com.soyomaker.xml;

import java.util.LinkedList;
import java.util.List;

/**
 * XMLObject
 * 
 * @author wp_g4
 * @since 20111117
 */
public class XMLObject {

	/** 名称 */
	private String name = "";
	/** 值 */
	private String value = "";
	/** 属性集 */
	private OrderedMap<String, String> attributeMap = new OrderedMap<String, String>();
	/** 子XMLObject对象集 */
	private List<XMLObject> childList = new LinkedList<XMLObject>();

	/**
	 * 添加子XMLObject对象
	 * 
	 * @param xmlObject
	 *            子XMLObject对象
	 */
	public void addChild(XMLObject xmlObject) {
		childList.add(xmlObject);
	}

	/**
	 * 获取属性值
	 * 
	 * @param name
	 *            属性名
	 * @return 属性值
	 */
	public String getAttribute(String name) {
		return attributeMap.get(name);
	}

	/**
	 * 获取属性列表
	 * 
	 * @return 属性列表
	 */
	public String[] getAttributeList() {
		return attributeMap.getAllKeys().toArray(new String[0]);
	}

	/**
	 * 获取子XMLObject对象
	 * 
	 * @param index
	 *            序号
	 * @return 子XMLObject对象
	 */
	public XMLObject getChild(int index) {
		return childList.get(index);
	}

	/**
	 * 获取第一个名称为 name 的子XMLObject对象
	 * 
	 * @param name
	 *            名称
	 * @return 子XMLObject对象
	 */
	public XMLObject getChild(String name) {
		XMLObject target = null;
		int len = childList.size();
		for (int i = 0; i < len; i++) {
			XMLObject temp = childList.get(i);
			if (temp.getName().equals(name)) {
				target = temp;
				break;
			}
		}
		return target;
	}

	/**
	 * 获取第 index 个名称为 name 的子XMLObject对象
	 * 
	 * @param name
	 *            名称
	 * @param index
	 *            序号
	 * @return 子XMLObject对象
	 */
	public XMLObject getChild(String name, int index) {
		XMLObject target = null;
		int len = childList.size();
		int num = -1;
		for (int i = 0; i < len; i++) {
			XMLObject temp = childList.get(i);
			if (temp.getName().equals(name)) {
				num++;
				if (num == index) {
					target = temp;
					break;
				}
			}
		}
		return target;
	}

	/**
	 * 获取子XMLObject对象列表
	 * 
	 * @return 子XMLObject对象列表
	 */
	public XMLObject[] getChildList() {
		return childList.toArray(new XMLObject[childList.size()]);
	}

	/**
	 * 获取子XMLObject的数量
	 * 
	 * @return 子XMLObject的数量
	 */
	public int getChildNumber() {
		return childList.size();
	}

	/**
	 * 获取XMLObject对象的名称
	 * 
	 * @return XMLObject对象的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取XMLObject对象的值
	 * 
	 * @return XMLObject对象的值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void putAttribute(String name, String value) {
		attributeMap.put(name, value);
	}

	/**
	 * 移除属性
	 * 
	 * @param name
	 *            属性名
	 */
	public void removeAttribute(String name) {
		attributeMap.remove(name);
	}

	/**
	 * 设置XMLObject对象的名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置XMLObject对象的值
	 * 
	 * @param value
	 *            值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean hasSonXMLObject = childList.size() != 0 || !value.equals("");
		// 拼装头尾
		String src = hasSonXMLObject ? ("<" + name + ">" + "</" + name + ">")
				: ("<" + name + "/>");
		// 拼装属性
		String attr = "";
		for (String name : attributeMap.getAllKeys()) {
			attr += " " + name + "=" + "\"" + attributeMap.get(name) + "\"";
		}
		// 拼装子结点
		String child = "";
		for (XMLObject xo : childList) {
			child += xo.toString();
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
		sb.insert(sb.length() - name.length() - 3, child);
		return sb.toString();
	}
}
