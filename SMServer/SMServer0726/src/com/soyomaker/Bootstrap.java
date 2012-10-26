package com.soyomaker;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

	private static Logger logger = Logger.getLogger(Bootstrap.class);

	public static void main(String[] args) {
		String[] applications = { "applicationContext.xml",
				"applicationContext-mina.xml","applicationContext-protocol.xml" };
		try {
			logger.info("系统准备启动");
			new ClassPathXmlApplicationContext(applications);
			logger.info("系统成功");
		} catch (Exception e) {
			logger.error("系统启动失败", e);
		}
	}

}
