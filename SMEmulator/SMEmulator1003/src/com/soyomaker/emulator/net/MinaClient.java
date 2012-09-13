package com.soyomaker.emulator.net;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
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

	private static String SN_KEY = "sn";

	private int sn = 0;// 全局消息序列号 自动递增

	private Map<Integer, GameObject> msgSentMap = new HashMap<Integer, GameObject>();// 发出包缓存

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
		} else {
			this.connect();
		}
	}

	private void sendMessageQueue() {
		while (messageQueue.size() > 0) {
			GameObject msgSent = messageQueue.poll();
			msgSent.putInt(SN_KEY, sn);
			msgSentMap.put(sn, msgSent);
			this.session.write(msgSent);
			sn++;
		}
	}

	public void disconnect() {
		if (this.session != null) {
			if (this.session.isConnected()) {
				this.session.close(true);
			}
			this.session = null;
		}
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
				MinaClient.this.msgSentMap.clear();
			}

			@Override
			public void messageSent(IoSession arg0, Object obj) throws Exception {
				GameObject msg = (GameObject) obj;
				SMLog.getInstance().info("发出消息:" + msg.getType() + msg.toJson());
			}

			@Override
			public void messageReceived(IoSession arg0, Object obj) throws Exception {
				GameObject msg = (GameObject) obj;
				if (msg.containsKey(SN_KEY)) {
					int sn = msg.getInt(SN_KEY);
					GameObject msgSent = MinaClient.this.msgSentMap.remove(sn);
					msgSent.remove(SN_KEY);
					msg.putObject("msgSent", msgSent);
					msg.remove(SN_KEY);
				}
				SMLog.getInstance().info("收到消息:" + msg.getType() + msg.toJson());
				NetTransceiver.getInstance().dispatchMessage(msg);
			}

		});

		// 连接到服务器：
		connector.connect(new InetSocketAddress(this.ip, this.port));

	}
}
