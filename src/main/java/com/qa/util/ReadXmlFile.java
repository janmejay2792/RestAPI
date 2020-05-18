/**
 * @author janmejay
 * 
 */
package com.qa.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXmlFile {

	@Test
	public static String readXML(String className, String tagName)
			throws ParserConfigurationException, SAXException, IOException {

		File testCases = new File(System.getProperty("ExecutionData"));
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(testCases);
		doc.getDocumentElement().normalize();
		NodeList nodes = doc.getElementsByTagName("test");
		String value = null;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			Element element = (Element) node;
			if (getValue("RunStatus", element).equalsIgnoreCase("Yes")
					&& getValue("testName", element).equalsIgnoreCase(className)) {
				value = getValue(tagName, element);
			}
		}
		return value;

	}

	public static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}