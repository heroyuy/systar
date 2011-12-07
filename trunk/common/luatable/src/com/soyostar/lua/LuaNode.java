package com.soyostar.lua;

import com.soyostar.lua.util.LuaFileUtil;

/**
 * Lua表结点
 * 
 * @author wp_g4
 * 
 */
public class LuaNode {

	private String key = null;// 键

	private Object value = null;// 值

	/**
	 * 无参构造
	 */
	public LuaNode() {
	}

	/**
	 * 构造器
	 * 
	 * @param value
	 *            值
	 */
	public LuaNode(Object value) {
		this.value = value;
	}

	/**
	 * 构造器
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public LuaNode(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 获取键
	 * 
	 * @return 键
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置键
	 * 
	 * @param key
	 *            键
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取值
	 * 
	 * @return 值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置值
	 * 
	 * @param value
	 *            值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		// (1) key
		if (key != null && !key.equals("")) {
			sb.append(key + "=");
		}
		// (2) value
		sb.append(value);
		// (3) 格式
		if (value instanceof String) {
			sb.insert(sb.length() - ((String) value).length(), "\"");
			sb.insert(sb.length(), "\"");
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LuaTable lt = new LuaTable();
		lt.addNode("id", 101);
		lt.addNode("name", "大剑");
		int[] status = { 1, 12, 3, 42, 12, 123 };
		lt.addNode(status);
		LuaNode ln = new LuaNode("item", lt);
		System.out.println(ln);
		LuaFileUtil.writeToFile(ln, "res/item.gat");
	}

}
