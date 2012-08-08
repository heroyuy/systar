package com.soyomaker.net;

import com.soyomaker.lang.GameObject;
import com.soyomaker.lang.IService;

public interface INetService extends IService {

	public void sendMessage(UserSession session, GameObject msg);

}
