package com.soyomaker.emulator.net;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.soyomaker.lang.GameObject;

public class MinaClient {

	private String ip = null;

	private int port = 0;

	private NetListener listener = null;

	private IoSession session = null;

	public NetListener getListener() {
		return listener;
	}

	public void setListener(NetListener listener) {
		this.listener = listener;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public MinaClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	public void start() {
		// Create TCP/IP connection
		NioSocketConnector connector = new NioSocketConnector();

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		chain.addLast("myChin", new ProtocolCodecFilter(new GameObjectCodecFactory()));

		// 消息处理器
		connector.setHandler(new IoHandler() {

			@Override
			public void sessionOpened(IoSession arg0) throws Exception {
				if (listener != null) {
					listener.onConnected();
				}
			}

			@Override
			public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {

			}

			@Override
			public void sessionCreated(IoSession arg0) throws Exception {

			}

			@Override
			public void sessionClosed(IoSession arg0) throws Exception {
				if (listener != null) {
					listener.onDisconnected();
				}
			}

			@Override
			public void messageSent(IoSession arg0, Object arg1) throws Exception {

			}

			@Override
			public void messageReceived(IoSession arg0, Object msg) throws Exception {
				if (listener != null) {
					listener.onMessageReceived((GameObject) msg);
				}
			}

			@Override
			public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

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

}
