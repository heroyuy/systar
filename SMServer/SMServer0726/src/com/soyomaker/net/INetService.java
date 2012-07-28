package com.soyomaker.net;

import com.soyomaker.application.IService;
import com.soyomaker.data.ISMObject;

public interface INetService extends IService {

	public void sendMessage(ISMObject msg);
}
