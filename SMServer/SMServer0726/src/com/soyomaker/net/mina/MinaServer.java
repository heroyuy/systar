package com.soyomaker.net.mina;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.INetService;
import com.soyomaker.net.PlayerSession;

public class MinaServer implements INetService {
	private SocketAcceptor acceptor = new NioSocketAcceptor();

	private int readBufferSize = 100000;
	private int port = 8081;

	@Override
	public void start() {
		try {
			acceptor.setReuseAddress(true);
			acceptor.getSessionConfig().setReadBufferSize(readBufferSize);

			ProtocolCodecFactory codecFactory = new GDSCodecFactory();
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(codecFactory));
			acceptor.setHandler(new MinaHandler());

			acceptor.bind(new InetSocketAddress(port));
			Logger.getLogger(getClass()).debug(
					"socket server started on " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
		acceptor.unbind();
	}

	@Override
	public void sendMessage(PlayerSession playerSession, ISMObject msg) {
		playerSession.getIoSession().write(msg);
	}

}
