package com.soyomaker.server.connector.handler;

import java.util.Map;

import com.soyomaker.data.IGObject;
import com.soyomaker.server.connector.GUSession;
import com.soyomaker.server.session.PlayerSessionManager;

public interface IRequestHandlerFactory {

	public abstract IRequestHandler getDefaultHandler();

	public abstract Map<String, IRequestHandler> getHandlers();

	public abstract ILoginHandler getLoginHandler();

	public abstract PlayerSessionManager getPlayerSessionManager();

	public abstract void handleMessage(GUSession session, IGObject obj);

	public abstract void registerRequestHandler(String key, IRequestHandler reqHandler);

	public abstract void setDefaultHandler(IRequestHandler defaultHandler);

	public abstract void setLoginHandler(ILoginHandler handler);

	public abstract void setPlayerSessionManager(PlayerSessionManager playerSessionManager);

}