package com.soyomaker.net;

import com.soyomaker.application.IBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.UserSession;

public interface INetService extends IBean {

	public void start();

	public void sendMessage(UserSession playerSession, ISMObject msg);
}
