package com.soyomaker.server.mina;

public class MinaConfig {
	/**
	 * Mina绑定的端口
	 */
	private int port;
	/**
	 * 读缓存大小，用于设置IoSessionConfig
	 */
	private int readBufferSize;

	public int getPort() {
		return port;
	}

	public int getReadBufferSize() {
		return readBufferSize;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setReadBufferSize(int bufferSize) {
		this.readBufferSize = bufferSize;
	}

}
