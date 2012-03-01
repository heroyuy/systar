package com.soyomaker.xml.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyomaker.xml.XMLObject;
import com.soyomaker.xml.XMLParser;

public class XMLReadTest {

	public static void main(String[] args) {
		try {
			XMLObject xmlObject = XMLParser.parse(new File("xml/xml1.xml"));
			System.out.println(xmlObject.getChild("controls").getChild("control",0).toString());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
