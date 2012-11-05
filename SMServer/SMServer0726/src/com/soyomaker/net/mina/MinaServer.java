package com.soyomaker.net.mina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.mina.transport.socket.SocketAcceptor;
import org.springframework.stereotype.Component;

import com.soyomaker.lang.GameObject;
import com.soyomaker.net.INetService;
import com.soyomaker.net.session.UserSession;

@Component(value="minaServer")
public class MinaServer implements INetService {
	
	private SocketAcceptor acceptor;
	
	public void setAcceptor(SocketAcceptor acceptor) {
		this.acceptor = acceptor;
	}
	
	public static void main(String[] args) {
		Person p=new Person();
		p.name="chenwentao";
		HashMap  map=new HashMap();
		map.put("1", p);
		List<Person> persons=new ArrayList<Person>();
		persons.addAll(map.values());
		
	Person p2=	persons.get(0);
	p2.name="dddd";
	
	System.out.println(p);
	System.out.println(p2);
	
	System.out.println(p.name);
	System.out.println(p2.name);
	}
	
	static class Person{
		
		public String name;
	}

	@Override
	public void start() {
		/*
		 * acceptor.setReuseAddress(IS_REUSE);
			acceptor.getSessionConfig().setReadBufferSize(READ_BUFFE_RSIZE);
			ProtocolCodecFactory codecFactory = new GameObjectCodecFactory();
			acceptor.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(codecFactory));
			acceptor.setHandler(new MinaHandler());
			acceptor.bind(new InetSocketAddress(PORT));
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
