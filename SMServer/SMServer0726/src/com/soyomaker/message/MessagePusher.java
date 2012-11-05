package com.soyomaker.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.session.UserSession;

/**
 * 消息推送器。用于服务器主动向客户端发消息
 * 
 * @author wp_g4
 * 
 */

@Component("messagePusher")
public class MessagePusher {

	/**
	 * 更新NPC状态
	 */
	private static final String PROTOCOL_ID_UPDATE_NPC_STATUS = "106001";

	/**
	 * 发送公告
	 */
	private static final String PROTOCOL_ID_SEND_NOTICE = "104002";

	@Autowired
	protected NetTransceiver netTransceiver;

	/**
	 * 发送NPC状态信息
	 * 
	 * @param session
	 */
	public void updateNpcStatus(UserSession session) {
		netTransceiver.pushMessage(session, new GameObject(
				PROTOCOL_ID_UPDATE_NPC_STATUS));
	}

	/**
	 * 发送公告
	 */
	public void sendNotice() {
		netTransceiver.pushMessage(null,
				new GameObject(PROTOCOL_ID_SEND_NOTICE));
	}
}
