package com.soyomaker.net.mina;

import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.soyomaker.application.IService;

public class MinaServer implements IService {
	private SocketAcceptor acceptor = new NioSocketAcceptor();

	private int readBufferSize = 100000;
	private int port = 9090;

	public void start() {
		try {
			acceptor.setReuseAddress(true);
			acceptor.getSessionConfig().setReadBufferSize(readBufferSize);

			ProtocolCodecFactory codecFactory = new GDSCodecFactory();
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(codecFactory));
			acceptor.setHandler(new MinaHandler());

			acceptor.bind(new InetSocketAddress(port));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		acceptor.unbind();
	}

}
