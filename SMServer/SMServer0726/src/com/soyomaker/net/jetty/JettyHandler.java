package com.soyomaker.net.jetty;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.net.NetTransceiver;
import com.soyomaker.net.PackageConst;

/**
 * Jetty所使用的MessageHandler
 * 
 * @author wp_g4
 * 
 */
public class JettyHandler extends AbstractBean {

	private Logger log = Logger.getLogger(JettyHandler.class);

	@Override
	public void initialize() {
	}

	public void messageReceived(HttpSession session, ISMObject message) {
		if (message != null) {
			log.debug("Jetty收到包:" + message);
			String type = message.getType();
			if (type.equals(PackageConst.PACKAGE_TYPE_NAME)) {
				// 多包
				Collection<ISMObject> c = message
						.getObjectArray(PackageConst.PACKAGE_ARRAY_KEY);
				for (ISMObject msg : c) {
					NetTransceiver.getInstance().dispatchMessage(msg);
				}
			} else {
				// 单包
				NetTransceiver.getInstance().dispatchMessage(message);
			}

		}
	}

}
