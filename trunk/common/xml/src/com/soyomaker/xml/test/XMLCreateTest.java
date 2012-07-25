package com.soyomaker.xml.test;

import com.soyomaker.xml.XMLObject;

public class XMLCreateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLObject xmlObject = new XMLObject();
		xmlObject.setName("wp");
		xmlObject.putAttribute("id", "LS088");
		xmlObject.putAttribute("age", "25");
		XMLObject xmlObject1 = new XMLObject();
		xmlObject1.setName("phone");
		xmlObject1.putAttribute("type", "lephone");
		xmlObject.addChild(xmlObject1);
		XMLObject xmlObject2 = new XMLObject();
		xmlObject2.setName("phone");
		xmlObject2.putAttribute("type", "itouch");
		xmlObject.addChild(xmlObject2);
		System.out.println(xmlObject.toString());
	}

}
