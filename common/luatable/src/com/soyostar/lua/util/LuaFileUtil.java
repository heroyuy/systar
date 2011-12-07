package com.soyostar.lua.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.soyostar.lua.LuaNode;
import com.soyostar.lua.LuaTable;

/**
 * Lua文件工具类
 * 
 * @author wp_g4
 * 
 */
public class LuaFileUtil {

	/**
	 * 将luaTable对象写入文件
	 * 
	 * @param luaTable
	 *            luaTable对象
	 * @param path
	 *            路径
	 */
	public static void writeToFile(LuaTable luaTable, String path) {
		try {
			FileWriter fw = new FileWriter(new File(path));
			fw.write(luaTable.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将luaNode对象写入文件
	 * 
	 * @param luaNode
	 *            luaNode对象
	 * @param path
	 *            路径
	 */
	public static void writeToFile(LuaNode luaNode, String path) {
		try {
			FileWriter fw = new FileWriter(new File(path));
			fw.write(luaNode.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
