package com.sergeiyarema.parser.deviceparser.realisations;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceparser.DeviceParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMDeviceParser extends DeviceParser {

    public DOMDeviceParser() {
        super();
    }

    @Override
    public Device parseRealisation(String xmlPath) throws IllegalArgumentException {
        File xmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);
            getInfoAboutAllNodes(document.getChildNodes());
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error while reading file: " + e.getMessage());
        }

        return handler.getParseResult();
    }

    private void getInfoAboutAllNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Node.TEXT_NODE) {
                String textInformation = node.getNodeValue().replace("\n", "").trim();

                if (!textInformation.isEmpty()) {
                    handler.onTagStart(node.getParentNode().getNodeName());
                    handler.setTag(list.item(i).getNodeValue());
                }
            } else {

                NamedNodeMap attributes = node.getAttributes();

                handler.onTagStart(node.getNodeName());

                for (int k = 0; k < attributes.getLength(); k++) {
                    Node curAttribute = attributes.item(k);
                    handler.setAttribute(curAttribute.getNodeName(), curAttribute.getTextContent());
                }

                if (node.hasChildNodes()) getInfoAboutAllNodes(node.getChildNodes());
                handler.onTagEnd(node.getNodeName());
            }
        }
    }
}

