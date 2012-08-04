package com.soyomaker.emulator.net;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.mina.GameObjectCodecFactory;

public class MinaClient {

	private String ip = null;

	private int port = 0;

	private IoSession session = null;

	public MinaClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void start() {
		// Create TCP/IP connection
		NioSocketConnector connector = new NioSocketConnector();

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		chain.addLast("myChin", new ProtocolCodecFilter(new GameObjectCodecFactory()));

		// 消息处理器
		connector.setHandler(new IoHandlerAdapter() {
			public void messageReceived(IoSession arg0, Object msg) throws Exception {
				NetTransceiver.getInstance().dispatchMessage((GameObject) msg);
			}
		});

		// 连接到服务器：
		ConnectFuture cf = connector.connect(new InetSocketAddress(this.ip, this.port));

		// Wait for the connection attempt to be finished.
		cf.awaitUninterruptibly();

		this.session = cf.getSession();

		this.session.getCloseFuture().awaitUninterruptibly();

		connector.dispose();
	}

	public void sendMessage(GameObject msg) {
		if (this.session != null) {
			this.session.write(msg);
		}
	}

	public void stop() {
		// TODO
	}

}
