package com.soyostar.xml;
/**
 * 找不到属性
 * @author wp_g4
 *
 */
public class NoSuchAttributeException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchAttributeException() {
		super();
	}

	public NoSuchAttributeException(String msg) {
		super(msg);
	}

}
