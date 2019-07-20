package com.banana.configuration.service;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationService {

	public static ConfigurationService createInstance() {
		return new ConfigurationService();
	}

	public String getHost() {
		try {
			File xmlFile = new File(ConfigurationService.class.getClassLoader().getResource("configuration.xml").getFile());
			Document doc = createDocumentFromXmlFile(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("configuration");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				return eElement.getElementsByTagName("host").item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getAccount() {
		try {
			File xmlFile = new File(ConfigurationService.class.getClassLoader().getResource("configuration.xml").getFile());
			Document doc = createDocumentFromXmlFile(xmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("configuration");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				return eElement.getElementsByTagName("account").item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static Document createDocumentFromXmlFile(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		return doc;
	}

}
