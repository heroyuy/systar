package com.soyostar.lua.test;

import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LuaTable lt = new LuaTable();

		LuaTable ltIds = new LuaTable();
		ltIds.addNode(new int[] { 1, 2, 3 });

		lt.addNode("name", "双击设置动画名称");
		lt.addNode("imageIds", ltIds);
		System.out.println(lt);
	}

}
