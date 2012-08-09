package com.soyomaker.net.mina;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.INetService;
import com.soyomaker.net.UserSession;

public class MinaServer implements INetService {
	
	private static final Logger logger=Logger.getLogger(MinaServer.class);
	
	private static final int READ_BUFFE_RSIZE = 100000;
	
	private static final int PORT = 9090;
	
	private static final boolean IS_REUSE=true;
	
	private SocketAcceptor acceptor = new NioSocketAcceptor();

	@Override
	public void start() {
		try {
			acceptor.setReuseAddress(IS_REUSE);
			acceptor.getSessionConfig().setReadBufferSize(READ_BUFFE_RSIZE);
			ProtocolCodecFactory codecFactory = new GameObjectCodecFactory();
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(codecFactory));
			acceptor.setHandler(new MinaHandler());
			acceptor.bind(new InetSocketAddress(PORT));
			logger.debug(
					"socket server started on " + PORT);
		} catch (Exception e) {
			logger.error("Mina server starts fail", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void stop() {
		acceptor.unbind();
	}

	@Override
	public void sendMessage(UserSession session, GameObject msg) {
		session.getIoSession().write(msg);
	}

}
