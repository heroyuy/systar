package com.soyomaker.emulator.api;

import org.apache.log4j.Logger;

public class SMLog {

	private static Logger logger = Logger.getLogger("System");

	public void info(String msg) {
		logger.info(msg);
	}

	public void err(String msg) {
		logger.error(msg);
	}

}
