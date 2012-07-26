package com.soyomaker.server.connector.mina;

import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.application.IService;
import com.soyomaker.core.IGUObject;

public class MinaServer extends AbstractBean implements IService {
	private SocketAcceptor acceptor = new NioSocketAcceptor();

	private int readBufferSize = 100000;
	private int port = 9090;

	@Override
	public void doCommand(IGUObject command) {
		// TODO Auto-generated method stub

	}

	@Override
	public IGUObject getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initialize() {
		port = getIntParam("port", 9090);
		readBufferSize = getIntParam("readBufferSize", 100000);
	}

	public void start() {
		try {
			acceptor.setReuseAddress(true);
			acceptor.getSessionConfig().setReadBufferSize(readBufferSize);

			ProtocolCodecFactory codecFactory = new GDSCodecFactory();
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));
			MinaHandler minaHandler = (MinaHandler) this.getBeanFactory().getBean(this.getParam("handler"));
			acceptor.setHandler(minaHandler);

			acceptor.bind(new InetSocketAddress(port));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stop() {
		acceptor.unbind();
	}

}
