package com.gudigital.exception;

import java.util.ArrayList;
import java.util.List;

public class SFSErrorData {
	IErrorCode code;
	List<String> params;

	public SFSErrorData(IErrorCode code) {
		this.code = code;
		this.params = new ArrayList<String>();
	}

	public void addParameter(String parameter) {
		params.add(parameter);
	}

	public IErrorCode getCode() {
		return code;
	}

	public List<String> getParams() {
		return params;
	}

	public void setCode(IErrorCode code) {
		this.code = code;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}
}
