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
 * @since 20111117
 */
public class XMLParser {

	/**
	 * 将指定的XML字符串解析为XMLObject对象
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

	/**
	 * 将指定的文件解析为XMLObject对象
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
	 * 将指定的输入流解析为XMLObject对象
	 * 
	 * @param is
	 *            XML输入流
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
	 * 辅助方法。将指定的node解析为XMLObject对象
	 * 
	 * @param node
	 *            Dom中的结点
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
					xmlObject.putAttribute(n.getNodeName().replaceAll("\r", "")
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
				xmlObject.addChild(parse(nn));
			} else if (nn.getNodeType() == Node.TEXT_NODE) {
				xmlObject.setValue(nn.getNodeValue().replaceAll("\r", "")
						.replaceAll("\n", ""));
			}
		}
		return xmlObject;
	}
}
