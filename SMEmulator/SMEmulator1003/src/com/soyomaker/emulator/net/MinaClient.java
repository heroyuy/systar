package com.soyomaker.emulator.net;

import java.net.InetSocketAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.soyomaker.emulator.util.SMLog;
import com.soyomaker.lang.GameObject;
import com.soyomaker.net.mina.GameObjectCodecFactory;

public class MinaClient {

	private String ip = null;

	private int port = 0;

	private IoSession session = null;

	private Queue<GameObject> messageQueue = new ConcurrentLinkedQueue<GameObject>();

	private boolean connecting = false;

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

	public void sendMessage(GameObject msg) {
		this.messageQueue.offer(msg);

		if (this.session != null && this.session.isConnected()) {
			this.sendMessageQueue();
		}
	}

	private void sendMessageQueue() {
		while (messageQueue.size() > 0) {
			this.session.write(messageQueue.poll());
		}
	}

	public void disconnect() {
		this.session.close(true);
		this.session = null;
	}

	public void connect() {
		if (this.connecting) {
			return;
		}
		this.connecting = true;
		// Create TCP/IP connection
		NioSocketConnector connector = new NioSocketConnector();

		DefaultIoFilterChainBuilder chain = connector.getFilterChain();

		chain.addLast("codec", new ProtocolCodecFilter(new GameObjectCodecFactory()));

		// 消息处理器
		connector.setHandler(new IoHandlerAdapter() {

			@Override
			public void sessionOpened(IoSession session) throws Exception {
				MinaClient.this.session = session;
				MinaClient.this.connecting = false;
				MinaClient.this.sendMessageQueue();
			}

			@Override
			public void sessionClosed(IoSession arg0) throws Exception {
				MinaClient.this.session = null;
			}

			@Override
			public void messageSent(IoSession arg0, Object msg) throws Exception {
				SMLog.getInstance().info("发出消息:" + ((GameObject) msg).toJson());
			}

			@Override
			public void messageReceived(IoSession arg0, Object msg) throws Exception {
				SMLog.getInstance().info("收到消息:" + ((GameObject) msg).toJson());
				NetTransceiver.getInstance().dispatchMessage((GameObject) msg);
			}

		});

		// 连接到服务器：
		connector.connect(new InetSocketAddress(this.ip, this.port));

	}

}
