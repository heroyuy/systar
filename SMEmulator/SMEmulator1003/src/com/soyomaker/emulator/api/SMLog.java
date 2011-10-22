package com.soyomaker.emulator.api;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SMLog {

	private static Logger log = Logger.getLogger("SMScript");

	public SMLog() {
		BasicConfigurator.configure();// 自动快速地使用缺省Log4j环境。
		PropertyConfigurator.configure("config/log4j.properties");// 读取使用Java的特性文件编写的配置文件。
	}

	public void err(String msg) {
		log.error(msg);
	}

	public void info(String msg) {
		log.info(msg);
	}

}
