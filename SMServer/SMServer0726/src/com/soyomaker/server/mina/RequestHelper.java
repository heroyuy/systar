package com.soyomaker.server.mina;

import com.soyomaker.data.IGObject;

public class RequestHelper {
	public static final String REQ_TYPE = "QQLX";

	public static String getReqType(IGObject obj) {
		return obj.getString(REQ_TYPE);
	}
}
