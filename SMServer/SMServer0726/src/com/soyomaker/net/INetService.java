package com.soyomaker.net;

import com.soyomaker.common.IService;
import com.soyomaker.data.ISMObject;

public interface INetService extends IService {

	public void sendMessage(PlayerSession playerSession, ISMObject msg);

}
