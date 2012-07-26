package com.gudigital.exception;

public class GUException extends Exception {
	/**
     * 
     */
	private static final long serialVersionUID = 6052949605652105170L;

	SFSErrorData errorData;

	public GUException() {
		super();
		errorData = null;
	}

	public GUException(String message) {
		super(message);
		errorData = null;
	}

	public GUException(String message, SFSErrorData data) {
		super(message);
		errorData = data;
	}

	public GUException(Throwable t) {
		super(t);
		errorData = null;
	}

	public SFSErrorData getErrorData() {
		return errorData;
	}
}
