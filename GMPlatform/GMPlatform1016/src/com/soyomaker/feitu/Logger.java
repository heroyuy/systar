package com.soyomaker.feitu;

import java.util.ArrayList;
import java.util.Collection;

public class Logger {

	private static Logger instance = new Logger();

	public static Logger getInstance() {
		return instance;
	}

	private Collection<ILog> logs = new ArrayList<ILog>();

	private Logger() {

	}

	public void addLog(ILog log) {
		logs.add(log);
	}

	public void info(Object msg) {
		for (ILog log : logs) {
			log.info(msg);
		}
	}

}
