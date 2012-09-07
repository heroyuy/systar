/**
 * 
 */
package com.soyomaker.config;

/**
 * 系统全局配置类,由spring对他进行配置
 * 
 * @author chenwentao
 * 
 */
public class Config {

	private static boolean debug;

	public static boolean isDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		Config.debug = Boolean.valueOf(debug);
	}

}
