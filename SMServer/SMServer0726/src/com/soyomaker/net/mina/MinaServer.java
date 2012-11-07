package com.soyomaker.net.mina;

import org.apache.mina.transport.socket.SocketAcceptor;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.INetService;
import com.soyomaker.net.session.UserSession;

@Component(value = "minaServer")
public class MinaServer implements INetService {

	private SocketAcceptor acceptor;

	public void setAcceptor(SocketAcceptor acceptor) {
		this.acceptor = acceptor;
	}

	@Override
	public void start() {
		/*
		 * acceptor.setReuseAddress(IS_REUSE);
		 * acceptor.getSessionConfig().setReadBufferSize(READ_BUFFE_RSIZE);
		 * ProtocolCodecFactory codecFactory = new GameObjectCodecFactory();
		 * acceptor.getFilterChain().addLast("codec", new
		 * ProtocolCodecFilter(codecFactory)); acceptor.setHandler(new
		 * MinaHandler()); acceptor.bind(new InetSocketAddress(PORT));
		 */
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
