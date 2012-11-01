package com.soyomaker.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.rribbit.RequestResponseBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 系统公告Task
 * 
 * @author chenwentao
 * 
 */
@Component("messageTask")
public class MessageTask {

	private static final String DATE_TYPE = "yyyy-MM-dd HH:mm";

	@Autowired
	private RequestResponseBus rrb;

	@Scheduled(fixedDelay = 300000)
	public void sendNotification() {
		rrb.send("doPayment", "1231");
	}

	private String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
