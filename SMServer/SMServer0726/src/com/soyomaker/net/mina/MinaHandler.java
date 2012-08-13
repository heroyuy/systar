package com.soyomaker.net.mina;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.UserSession;

/**
 * Mina所使用的IoHandler的实现。
 * 
 * @author wp_g4
 * 
 */
public class MinaHandler implements IoHandler {

	private static final Logger logger = Logger.getLogger(MinaHandler.class);
	
	private NetTransceiver netTransceiver;

	public void setNetTransceiver(NetTransceiver netTransceiver) {
		this.netTransceiver = netTransceiver;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("exceptionCaught");
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		if (obj instanceof GameObject) {
			UserSession userSession = (UserSession) session
					.getAttribute("userSession");
			if (userSession == null) {
				userSession = new UserSession(session);
				session.setAttribute("userSession", userSession);
			}
			GameObject message = (GameObject) obj;
			logger.debug("Mina收到包:" + message.getType() + message.toJson());
			String type = message.getType();
			if (type.equals(PackageConst.PACKAGE_TYPE_NAME)) {
				// 多包
				Collection<GameObject> c = message
						.getObjectArray(PackageConst.PACKAGE_ARRAY_KEY);
				for (GameObject msg : c) {
					netTransceiver.dispatchMessage(userSession,
							msg);
				}
			} else {
				// 单包
				netTransceiver.dispatchMessage(userSession,
						message);
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		GameObject msg = (GameObject) message;
		logger.debug("Mina发出包:" + msg.getType() + msg.toJson());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.debug("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.debug("sessionCreated");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		logger.debug("sessionIdle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.debug("sessionOpened");
	}
}
