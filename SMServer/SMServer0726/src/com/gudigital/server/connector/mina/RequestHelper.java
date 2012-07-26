package com.gudigital.server.connector.mina;

import com.gudigital.core.IGUObject;

public class RequestHelper {
	public static final String REQ_TYPE = "QQLX";

	public static String getReqType(IGUObject obj) {
		return obj.getString(REQ_TYPE);
	}
}
