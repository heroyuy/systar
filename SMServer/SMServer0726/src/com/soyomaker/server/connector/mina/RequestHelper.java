package com.soyomaker.server.connector.mina;

import com.soyomaker.core.IGUObject;

public class RequestHelper {
	public static final String REQ_TYPE = "QQLX";

	public static String getReqType(IGUObject obj) {
		return obj.getString(REQ_TYPE);
	}
}
