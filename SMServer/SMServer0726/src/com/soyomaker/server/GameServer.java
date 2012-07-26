package com.soyomaker.server;

import org.apache.log4j.PropertyConfigurator;

import com.soyomaker.application.BeanFactory;
import com.soyomaker.application.IBean;
import com.soyomaker.application.IService;
import com.soyomaker.server.handler.ILoginHandler;
import com.soyomaker.server.handler.IRequestHandler;
import com.soyomaker.server.handler.IRequestHandlerFactory;
import com.soyomaker.server.handler.LoginHandler;
import com.soyomaker.server.handler.RequestHandlerFactory;
import com.soyomaker.server.jetty.JettyHandler;
import com.soyomaker.server.jetty.JettyServer;
import com.soyomaker.server.mina.MinaHandler;
import com.soyomaker.server.mina.MinaServer;
import com.soyomaker.server.session.PlayerSessionManager;

public class GameServer {
	private BeanFactory beanFactory = new BeanFactory();
	private IRequestHandlerFactory reqHandlerFactory = null;

	private static GameServer server = new GameServer();

	public static GameServer instance() {
		return server;
	}

	public static void main(String[] args) {
		server.init("res/server.xml");
		PropertyConfigurator.configure("res/log4j.properties");
		server.start();
		System.out.println("Server started....");
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void init(String configFiles) {
		beanFactory.addBean("socketServer", MinaServer.class);
		beanFactory.addBean("minaHandler", MinaHandler.class);
		beanFactory.addBean("httpServer", JettyServer.class);
		beanFactory.addBean("jettyHandler", JettyHandler.class);
		beanFactory.addBean("requestHandlerFactory", RequestHandlerFactory.class);
		beanFactory.addBean("loginHandler", LoginHandler.class);
		beanFactory.addBean("sessionManager", PlayerSessionManager.class);
		// beanFactory.addBean("model", Model.class);
		// beanFactory
		// .addBean("eventService:sync", SyncEventService.class);
		// beanFactory.addBean("eventService:async",
		// AsyncEventService.class);
		// beanFactory.addBean("scheduleService", ScheduleService.class);
		// beanFactory.addBean("datasource:dbcp", GUDataSource.class);
		// beanFactory.addBean("typeFactory:typeFactory",
		// TypeHelperFactory.class);
		// beanFactory.addBean("playerProxy:table", TableProxy.class);
		// beanFactory.addBean("playerDataSet:player_dataSet",
		// DirectDataset.class);

		beanFactory.initBeansWithConfig(configFiles);

		reqHandlerFactory = (IRequestHandlerFactory) beanFactory.getBean("requestHandlerFactory");
	}

	public void registerReqHandler(String key, IRequestHandler reqHandler) {
		reqHandlerFactory.registerRequestHandler(key, reqHandler);
	}

	public void setLoginHandler(ILoginHandler loginHandler) {
		reqHandlerFactory.setLoginHandler(loginHandler);
	}

	public void start() {
		for (IBean b : beanFactory.getBeans()) {
			if (b instanceof IService) {
				((IService) b).start();
			}
		}
	}

	public void stop() {
		for (Object b : beanFactory.getBeans()) {
			if (b instanceof IService) {
				((IService) b).stop();
			}
		}
	}

}
