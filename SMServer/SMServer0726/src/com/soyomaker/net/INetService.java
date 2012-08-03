package com.soyomaker.net;

import com.soyomaker.common.IService;
import com.soyomaker.data.SMObject;

public interface INetService extends IService {

	public void sendMessage(PlayerSession playerSession, SMObject msg);

}
