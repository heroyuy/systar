package com.soyomaker.emulator.net;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {

	private String ip = null;

	private int port = 0;

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public IoHandler getHandler() {
		return handler;
	}

	private IoHandler handler = null;

	public MinaClient(String ip, int port, IoHandler handler) {
		super();
		this.ip = ip;
		this.port = port;
		this.handler = handler;
	}

	public void start() {
		// Create TCP/IP connection
		NioSocketConnector connector = new NioSocketConnector();

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		chain.addLast("myChin", new ProtocolCodecFilter(new GameObjectCodecFactory()));

		// 消息处理器
		connector.setHandler(new MinaHandler());

		// 连接到服务器：
		ConnectFuture cf = connector.connect(new InetSocketAddress(this.ip, this.port));

		// Wait for the connection attempt to be finished.
		cf.awaitUninterruptibly();

		cf.getSession().getCloseFuture().awaitUninterruptibly();

		connector.dispose();
	}

	public static void main(String[] args) {
		
	}

}
