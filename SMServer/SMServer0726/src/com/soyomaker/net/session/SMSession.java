package com.soyomaker.net.session;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.mina.core.session.IoSession;

import com.soyomaker.data.ISMObject;

/**
 * 这是一个复合对象，把各类网络层的Session整合在一起使用。 这个类的作用主要是代理，为底层Session提供统一的接口。
 * 
 * @author wp_g4
 * 
 */
public class SMSession {
	private SMSessionType type;
	private HttpSession httpSession;
	private IoSession minaSession;

	public SMSession(HttpSession s) {
		this.type = SMSessionType.HTTP_SESSION;
		this.httpSession = s;
	}

	public SMSession(IoSession s) {
		this.type = SMSessionType.MINA_SESSION;
		this.minaSession = s;
	}

	public void disconnect() {
		if (type == SMSessionType.HTTP_SESSION) {
			httpSession.invalidate();
		} else if (type == SMSessionType.MINA_SESSION) {
			minaSession.close(true);
		}
	}

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public IoSession getMinaSession() {
		return minaSession;
	}

	public PlayerSession getPlayerSession() {
		PlayerSession playerSession = null;
		if (this.getType() == SMSessionType.MINA_SESSION) {
			playerSession = (PlayerSession) getMinaSession().getAttribute("playerSession");
		} else if (getType() == SMSessionType.HTTP_SESSION) {
			playerSession = (PlayerSession) getHttpSession().getAttribute("playerSession");
		}
		return playerSession;
	}

	public SMSessionType getType() {
		return type;
	}

	public void putPlayerSession(PlayerSession session) {
		if (this.getType() == SMSessionType.MINA_SESSION) {
			getMinaSession().setAttribute("playerSession", session);
		} else if (getType() == SMSessionType.HTTP_SESSION) {
			getHttpSession().setAttribute("playerSession", session);
		}
	}

	public void sendMessage(ISMObject msg) {
		if (type == SMSessionType.HTTP_SESSION) {
			@SuppressWarnings("unchecked")
			List<ISMObject> msgs = (List<ISMObject>) httpSession.getAttribute("messages");
			msgs.add(msg);
		} else if (type == SMSessionType.MINA_SESSION) {
			minaSession.write(msg);
		}
	}

	public void setProperty(String key, Object value) {
		if (type == SMSessionType.MINA_SESSION) {
			minaSession.setAttribute(key, value);
		} else {
			httpSession.setAttribute(key, value);
		}
	}
}
