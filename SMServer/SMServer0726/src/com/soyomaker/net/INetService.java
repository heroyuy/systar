package com.soyomaker.net;

import com.soyomaker.common.GameObject;
import com.soyomaker.common.IService;

public interface INetService extends IService {

	public void sendMessage(PlayerSession playerSession, GameObject msg);

}
