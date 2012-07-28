package com.soyomaker.net.handler;

import java.util.Map;

import com.soyomaker.data.ISMObject;
import com.soyomaker.net.session.PlayerSessionManager;
import com.soyomaker.net.session.SMSession;

public interface IRequestHandlerFactory {

	public abstract IRequestHandler getDefaultHandler();

	public abstract Map<String, IRequestHandler> getHandlers();

	public abstract LoginHandler getLoginHandler();

	public abstract PlayerSessionManager getPlayerSessionManager();

	public abstract void handleMessage(SMSession session, ISMObject obj);

	public abstract void registerRequestHandler(String key, IRequestHandler reqHandler);

	public abstract void setDefaultHandler(IRequestHandler defaultHandler);

	public abstract void setLoginHandler(LoginHandler handler);

	public abstract void setPlayerSessionManager(PlayerSessionManager playerSessionManager);

}