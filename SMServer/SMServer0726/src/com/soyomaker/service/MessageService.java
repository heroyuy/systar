package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.message.Message;

/**
 * @author chenwentao
 * 
 */
@Service("messageService")
@Transactional
public class MessageService extends AbstractService<Message> {

	/**
	 * 查找未读取的系统通告
	 * 
	 * @return
	 */
	public List<Message> findUnreadNotification() {
		return find("from Message m where m.type=? and m.isRead=?",
				Message.SYSTEM_NOTIFICATION, Message.UNREAD);
	}
}
