package com.soyomaker.server.mina;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.server.PackageConst;
import com.soyomaker.server.handler.IRequestHandlerFactory;
import com.soyomaker.server.session.GUSession;

/**
 * Mina所使用的IoHandler的实现。
 * 
 * @author zhangjun
 * 
 */
public class MinaHandler extends AbstractBean implements IoHandler {

	private Logger log = Logger.getLogger(MinaHandler.class);

	private IRequestHandlerFactory handlerFactory;

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// log.error("exceptionCaught");
		cause.printStackTrace();
	}

	public IRequestHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	@Override
	public void initialize() {
		handlerFactory = (IRequestHandlerFactory) this.getBeanFactory().getBean(this.getParam("handlerFactory"));
	}

	@Override
	public void messageReceived(IoSession session, Object obj) throws Exception {
		log.debug("messageReceived");
		if (obj instanceof ISMObject) {
			ISMObject message = (ISMObject) obj;
			log.debug("Mina收到包:" + message);
			GUSession s = new GUSession(session);
			String type = message.getType();
			if (type.equals(PackageConst.PACKAGE_TYPE_NAME)) {
				// 多包
				Collection<ISMObject> c = message.getObjectArray(PackageConst.PACKAGE_ARRAY_KEY);
				for (ISMObject msg : c) {
					handlerFactory.handleMessage(s, msg);
				}
			} else {
				// 单包
				handlerFactory.handleMessage(s, message);
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.debug("Mina发出包::" + message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// log.debug("sessionClosed");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// log.debug("sessionCreated");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// log.debug("sessionIdle");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.debug("sessionOpened");
	}

	public void setHandlerFactory(IRequestHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

}
