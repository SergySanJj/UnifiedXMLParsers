package com.sergeiyarema.parser;


import com.sergeiyarema.parser.deviceinfo.Device;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javax.xml.stream.XMLStreamConstants.*;

public class StAXDeviceParser extends DeviceParser {

    public StAXDeviceParser(){}

    @Override
    public Device parse(String xmlPath) throws IllegalArgumentException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        XMLStreamReader xmlStreamReader;

        try {
            xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlPath));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error " + e.getMessage());
        }

        try {
            while (xmlStreamReader.hasNext()) {

                int event = xmlStreamReader.next();

                if (event == END_DOCUMENT) {
                    break;
                }
                if (event == START_ELEMENT) {

                    handler.onTagStart(xmlStreamReader.getLocalName());

                    for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
                        String value = xmlStreamReader.getAttributeValue(i);
                        String name = xmlStreamReader.getAttributeName(i).toString();
                        handler.setAttribute(name, value);
                    }
                }
                if (event == CHARACTERS) {
                    String information = xmlStreamReader.getText();
                    information = information.replace("\n", "").trim();

                    handler.setTag(information);
                }

                if (event == END_ELEMENT) {
                    handler.onTagEnd(xmlStreamReader.getLocalName());
                }
            }

        } catch (XMLStreamException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error " + e.getMessage());
        }
        return handler.getParseResult();
    }
}

