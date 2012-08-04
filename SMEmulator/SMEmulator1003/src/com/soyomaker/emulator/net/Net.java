package com.soyomaker.emulator.net;

public class Net {

	private static Net instance = new Net();

	public static Net getInstance() {
		return instance;
	}

	private NetService netService = null;

	private Net() {

	}

	public NetService getNetService() {
		return netService;
	}

	public void setNetService(NetService netService) {
		this.netService = netService;
	}

}
