package com.soyomaker.exception;

public class SFSRuntimeException extends RuntimeException {
	public SFSRuntimeException() {
		super();
	}

	public SFSRuntimeException(String message) {
		super(message);
	}

	public SFSRuntimeException(Throwable t) {
		super(t);
	}
}
