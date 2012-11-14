package com.soyomaker.message;

import org.rribbit.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soyomaker.event.EventIdConst;
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
	 * 切换地图
	 */
	private static final String PROTOCOL_ID_SWITCH_MAP = "103003";

	/**
	 * 发送聊天消息
	 */
	private static final String PROTOCOL_ID_SEND_CHAT_MESSAGE = "104001";

	/**
	 * 发送公告
	 */
	private static final String PROTOCOL_ID_SEND_NOTICE = "104002";

	/**
	 * 更新NPC状态
	 */
	private static final String PROTOCOL_ID_UPDATE_NPC_STATUS = "106001";

	@Autowired
	protected NetTransceiver netTransceiver;

	/**
	 * 切换地图
	 * 
	 * @param mapId
	 *            地图ID
	 * @param col
	 *            x坐标
	 * @param row
	 *            y坐标
	 */
	public void switchMap(UserSession session, int mapId, int col, int row) {
		GameObject msgPush = new GameObject(PROTOCOL_ID_SWITCH_MAP);
		msgPush.putInt("mapId", mapId);
		msgPush.putInt("col", col);
		msgPush.putInt("row", row);
		netTransceiver.dispatchPushMessage(session, msgPush);
	}

	/**
	 * 发送聊天消息
	 * 
	 * @param userSession
	 *            会话
	 * @param playerId
	 *            玩家ID
	 * @param playerName
	 *            玩家呢称
	 * @param content
	 *            消息内容
	 */
	public void sendChatMessage(UserSession session, int playerId,
			String playerName, String content) {
		GameObject msgPush = new GameObject(PROTOCOL_ID_SEND_CHAT_MESSAGE);
		msgPush.putInt("playerId", playerId);
		msgPush.putString("playerName", playerName);
		msgPush.putString("content", content);
		netTransceiver.dispatchPushMessage(session, msgPush);
	}

	/**
	 * 发送公告
	 */
	@Listener(hint = EventIdConst.EventIdNoticePolling)
	public void sendNotice() {
		netTransceiver.dispatchPushMessage(null, new GameObject(
				PROTOCOL_ID_SEND_NOTICE));
	}

	/**
	 * 发送NPC状态信息
	 * 
	 * @param session
	 */
	public void updateNpcState(UserSession session) {
		netTransceiver.dispatchPushMessage(session, new GameObject(
				PROTOCOL_ID_UPDATE_NPC_STATUS));
	}
}
