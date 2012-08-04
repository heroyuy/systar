package com.soyomaker.emulator.net;

import com.soyomaker.lang.GameObject;

public interface NetListener {

	public void onConnected();

	public boolean onDisconnected();

	public void onMessageReceived(GameObject msg);

}
