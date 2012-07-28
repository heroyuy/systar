package com.soyomaker.server.jetty;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.server.PackageConst;
import com.soyomaker.server.handler.IRequestHandlerFactory;
import com.soyomaker.server.session.SMSession;

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
		handlerFactory = (IRequestHandlerFactory) this.getBeanFactory().getBean(this.getParam("handlerFactory"));
	}

	public void messageReceived(HttpSession session, ISMObject message) {
		if (message != null) {
			log.debug("Jetty收到包:" + message);
			SMSession s = new SMSession(session);
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

	public void setHandlerFactory(IRequestHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

}
