package com.soyomaker.net;

import com.soyomaker.data.ISMObject;

public interface INetService {

	public void start();

	public void sendMessage(ISMObject msg);
}
