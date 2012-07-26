package com.soyomaker.server.handler;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.soyomaker.application.AbstractBean;
import com.soyomaker.data.IGObject;
import com.soyomaker.server.session.GUSession;
import com.soyomaker.server.session.PlayerSession;
import com.soyomaker.server.session.PlayerSessionManager;

/**
 * 处理所有请求的类
 * 
 * @author zhangjun
 * 
 */
public class RequestHandlerFactory extends AbstractBean implements IRequestHandlerFactory {

	private Map<String, IRequestHandler> handlers = new HashMap<String, IRequestHandler>();
	private IRequestHandler defaultHandler;
	private LoginHandler loginHandler;
	private PlayerSessionManager playerSessionManager;

	private Logger log = Logger.getLogger(RequestHandlerFactory.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.server.connector.handler.IRequestHandlerFactory#
	 * getDefaultHandler()
	 */
	@Override
	public IRequestHandler getDefaultHandler() {
		return defaultHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gudigital.server.connector.handler.IRequestHandlerFactory#getHandlers
	 * ()
	 */
	@Override
	public Map<String, IRequestHandler> getHandlers() {
		return handlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gudigital.server.connector.handler.IRequestHandlerFactory#getLoginHandler
	 * ()
	 */
	@Override
	public LoginHandler getLoginHandler() {
		return loginHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.server.connector.handler.IRequestHandlerFactory#
	 * getPlayerSessionManager()
	 */
	@Override
	public PlayerSessionManager getPlayerSessionManager() {
		return playerSessionManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gudigital.server.connector.handler.IRequestHandlerFactory#handleMessage
	 * (com.gudigital.server.connector.GUSession, com.gudigital.core.IGUObject)
	 */
	@Override
	public void handleMessage(GUSession session, IGObject obj) {
		log.debug("RequestHandlerFactory处理包:" + obj);
		PlayerSession playerSession = session.getPlayerSession();
		if (playerSession == null) { // 用户是未登录状态
			long playerId = loginHandler.login(session, obj); // 调用验证方法
			if (playerId > 0) {
				playerSession = playerSessionManager.createSession(playerId);
				session.putPlayerSession(playerSession);
			}
		}

		String type = obj.getType();
		IRequestHandler handler = handlers.get(type);
		if (handler == null) {
			handler = defaultHandler;
		}
		if (handler != null) {
			handler.handleMessage(session, obj);
		}
		// 测试服务器原样返回收到的包
		session.sendMessage(obj);
	}

	@Override
	public void initialize() {
		playerSessionManager = (PlayerSessionManager) getBeanFactory().getBean("sessionManager");
		loginHandler = (LoginHandler) getBeanFactory().getBean("loginHandler");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.server.connector.handler.IRequestHandlerFactory#
	 * registerRequestHandler(java.lang.String,
	 * com.gudigital.server.connector.handler.IRequestHandler)
	 */
	@Override
	public void registerRequestHandler(String key, IRequestHandler reqHandler) {
		handlers.put(key, reqHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.server.connector.handler.IRequestHandlerFactory#
	 * setDefaultHandler(com.gudigital.server.connector.handler.IRequestHandler)
	 */
	@Override
	public void setDefaultHandler(IRequestHandler defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	public void setHandlers(Map<String, IRequestHandler> handlers) {
		this.handlers = handlers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gudigital.server.connector.handler.IRequestHandlerFactory#setLoginHandler
	 * (com.gudigital.server.connector.handler.ILoginHandler)
	 */
	@Override
	public void setLoginHandler(LoginHandler handler) {
		this.loginHandler = handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gudigital.server.connector.handler.IRequestHandlerFactory#
	 * setPlayerSessionManager
	 * (com.gudigital.server.session.PlayerSessionManager)
	 */
	@Override
	public void setPlayerSessionManager(PlayerSessionManager playerSessionManager) {
		this.playerSessionManager = playerSessionManager;
	}
}
