package com.soyomaker.net;

import com.soyomaker.common.IService;
import com.soyomaker.data.GameObject;

public interface INetService extends IService {

	public void sendMessage(PlayerSession playerSession, GameObject msg);

}
