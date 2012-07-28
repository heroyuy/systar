package com.soyomaker.net;

import com.soyomaker.application.IBean;
import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.PlayerSession;

public interface INetService extends IBean {

	public void start();

	public void sendMessage(PlayerSession playerSession, ISMObject msg);
}
