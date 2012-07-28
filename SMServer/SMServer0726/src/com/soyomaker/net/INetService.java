package com.soyomaker.net;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.PlayerSession;

public interface INetService {

	public void start();

	public void sendMessage(PlayerSession playerSession, ISMObject msg);
}
