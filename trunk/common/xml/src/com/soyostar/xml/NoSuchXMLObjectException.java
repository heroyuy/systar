package com.soyostar.xml;
/**
 * 找不到子XMLObject
 * @author wp_g4
 *
 */
public class NoSuchXMLObjectException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchXMLObjectException() {
		super();
	}

	public NoSuchXMLObjectException(String msg) {
		super(msg);
	}

}
