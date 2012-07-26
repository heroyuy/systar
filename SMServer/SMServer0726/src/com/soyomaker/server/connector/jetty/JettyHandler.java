package com.soyomaker.server.connector.jetty;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.core.IGUObject;
import com.soyomaker.server.connector.GUSession;
import com.soyomaker.server.connector.handler.IRequestHandlerFactory;
import com.soyomaker.server.connector.mina.CodecConst;

/**
 * Jetty所使用的MessageHandler
 * 
 * @author wp_g4
 * 
 */
public class JettyHandler extends AbstractBean {

	private IRequestHandlerFactory handlerFactory;

	private Logger log = Logger.getLogger(JettyHandler.class);

	public IRequestHandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		handlerFactory = (IRequestHandlerFactory) this.getBeanFactory().getBean(this.getParam("handlerFactory"));
	}

	public void messageReceived(HttpSession session, IGUObject message) {
		if (message != null) {
			log.debug("Jetty收到包:" + message);
			GUSession s = new GUSession(session);
			String type = message.getType();
			if (type.equals(CodecConst.PACKAGE_TYPE_NAME)) {
				// 多包
				Collection<IGUObject> c = message.getObjectArray(CodecConst.PACKAGE_ARRAY_KEY);
				for (IGUObject msg : c) {
					handlerFactory.handleMessage(s, msg);
				}
			} else {
				// 单包
				handlerFactory.handleMessage(s, message);
			}

		}
	}

	public void setHandlerFactory(IRequestHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

}
