package com.soyomaker.emulator.utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SMLog {

	private static Logger log = Logger.getLogger("SMLog");

	private static String CONFIG_PATH = "plugin/emulator/config/log4j.properties";

	private boolean debug;

	private static SMLog instance = new SMLog();

	public static SMLog getInstance() {
		return instance;
	}

	private SMLog() {
		BasicConfigurator.configure();// 自动快速地使用缺省Log4j环境。
		PropertyConfigurator.configure(CONFIG_PATH);// 读取使用Java的特性文件编写的配置文件。
	}

	public void error(Object msg) {
		if (debug) {
			log.error(msg);
		}
	}

	public void info(Object msg) {
		if (debug) {
			log.info(msg);
		}
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
