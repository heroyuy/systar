package com.soyostar.xml.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.soyostar.xml.XMLObject;
import com.soyostar.xml.XMLParser;

public class XMLReadTest {

	public static void main(String[] args) {
		try {
			XMLObject xmlObject = XMLParser.parse(new File("xml/xml1.xml"));
			System.out.println(xmlObject.toString());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
