package com.sergeiyarema.parser.deviceparser.realisations;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceparser.DefaultDeviceHandler;
import com.sergeiyarema.parser.deviceparser.DeviceParser;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


public class SAXDeviceParser extends DeviceParser {
    public SAXDeviceParser() {
        super();
    }

    @Override
    public Device parseRealisation(String xmlPath) throws IllegalArgumentException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        Device parseResult;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXHandlerProxy saxHandler = new SAXHandlerProxy(handler);
            saxParser.parse(new File(xmlPath), saxHandler);
            parseResult = handler.getParseResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
        return parseResult;
    }

    private static class SAXHandlerProxy extends DefaultHandler {
        private Device parseResult;
        private DefaultDeviceHandler handler;

        public SAXHandlerProxy(DefaultDeviceHandler newHandler) {
            handler = newHandler;
        }

        public Device getParseResult() {
            return parseResult;
        }

        @Override
        public void endDocument() {
            parseResult = handler.getParseResult();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            handler.onTagStart(qName);
            for (int iter = 0; iter < attributes.getLength(); iter++) {
                handler.setAttribute(attributes.getQName(iter), attributes.getValue(iter));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            handler.onTagEnd(qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String data = new String(ch, start, length);
            data = data.replace("\n", "").trim();
            handler.setTag(data);
        }
    }
}
