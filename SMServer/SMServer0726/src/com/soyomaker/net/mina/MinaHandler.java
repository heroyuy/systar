package com.soyomaker.net.mina;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.soyomaker.data.GameObject;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PlayerSession;

/**
 * Mina所使用的IoHandler的实现。
 * 
 * @author wp_g4
 * 
 */
public class MinaHandler implements IoHandler {

	private Logger log = Logger.getLogger(MinaHandler.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		log.error("exceptionCaught");
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		if (obj instanceof GameObject) {
			PlayerSession playerSession = (PlayerSession) session
					.getAttribute("playerSession");
			if (playerSession == null) {
				playerSession = new PlayerSession(session);
				session.setAttribute("playerSession", playerSession);
			}
			GameObject message = (GameObject) obj;
			log.debug("Mina收到包:" + message.toJson());
			String type = message.getType();
			if (type.equals(PackageConst.PACKAGE_TYPE_NAME)) {
				// 多包
				Collection<GameObject> c = message
						.getObjectArray(PackageConst.PACKAGE_ARRAY_KEY);
				for (GameObject msg : c) {
					NetTransceiver.getInstance().dispatchMessage(playerSession,
							msg);
				}
			} else {
				// 单包
				NetTransceiver.getInstance().dispatchMessage(playerSession,
						message);
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.debug("Mina发出包::" + ((GameObject) message).toJson());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.debug("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.debug("sessionCreated");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		log.debug("sessionIdle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.debug("sessionOpened");
	}
}
