package com.gudigital.util;

import org.apache.log4j.Logger;

public class Log4JUtil {
	private static boolean showTrace = false;

	public static void error(Object object, Exception e) {
		Logger.getLogger(object.getClass()).error("Error. Exception message: " + e.getMessage());

		if (showTrace) {
			StackTraceElement[] traces = e.getStackTrace();
			for (StackTraceElement t : traces) {
				Logger.getLogger(object.getClass()).error(t.toString());
			}
		}
	}

	public static void error(Object object, String message) {
		Logger.getLogger(object.getClass()).error(message);
	}

	public static void error(Object object, String message, Exception e) {
		Logger.getLogger(object.getClass()).error(message + " Exception message: " + e.getMessage());
	}

	public static boolean isShowTrace() {
		return showTrace;
	}

	public static void setShowTrace(boolean showTrace) {
		Log4JUtil.showTrace = showTrace;
	}

}
