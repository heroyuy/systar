package com.soyostar.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML解析器
 * 
 * @author wp_g4
 * 
 */
public class XMLParser {

	/**
	 * 解析指定文件为XMLObject
	 * 
	 * @param file
	 *            XML文件
	 * @return XMLObject对象
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static XMLObject parse(File file)
			throws ParserConfigurationException, SAXException, IOException {
		XMLObject xmlObject = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		xmlObject = parse(doc.getDocumentElement());
		return xmlObject;
	}

	/**
	 * 解析指定的输入流为XMLObject
	 * 
	 * @param is
	 *            输入流
	 * @return XMLObject对象
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static XMLObject parse(InputStream is)
			throws ParserConfigurationException, SAXException, IOException {
		XMLObject xmlObject = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(is);
		xmlObject = parse(doc.getDocumentElement());
		return xmlObject;
	}

	/**
	 * 辅助方法将指定的结点解析为XMLObject
	 * 
	 * @param node
	 *            结点
	 * @return XMLObject对象
	 */
	private static XMLObject parse(Node node) {
		XMLObject xmlObject = new XMLObject();

		// 设置类名
		xmlObject.setName(node.getNodeName().replaceAll("\r", "")
				.replaceAll("\n", ""));

		// 遍历属性
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.hasAttributes()) {
				Element e = (Element) node;
				NamedNodeMap nnm = e.getAttributes();
				for (int j = 0; j < nnm.getLength(); j++) {
					Node n = nnm.item(j);
					String aValue = n.getFirstChild().getNodeValue();
					xmlObject.addAttribute(n.getNodeName().replaceAll("\r", "")
							.replaceAll("\n", ""), aValue.replaceAll("\r", "")
							.replaceAll("\n", ""));

				}
			}
		}

		// 遍历子结点
		NodeList nl = node.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node nn = nl.item(i);
			if (nn.getNodeType() == Node.ELEMENT_NODE) {
				xmlObject.addSon(nn.getNodeName().replaceAll("\r", "")
						.replaceAll("\n", ""), parse(nn));
			} else if (nn.getNodeType() == Node.TEXT_NODE) {
				xmlObject.setValue(nn.getNodeValue().replaceAll("\r", "")
						.replaceAll("\n", ""));
			}
		}
		return xmlObject;
	}

	/**
	 * 将指定的XML字符串解析为XMLObject
	 * 
	 * @param xmlString
	 *            XML字符串
	 * @return XMLObject对象
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static XMLObject parse(String xmlString)
			throws ParserConfigurationException, SAXException, IOException {
		XMLObject xmlObject = null;
		xmlObject = parse(new ByteArrayInputStream(xmlString.getBytes()));
		return xmlObject;
	}

}
