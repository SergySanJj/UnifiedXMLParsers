package com.sergeiyarema.parser.deviceparser.realisations;

import com.sergeiyarema.parser.deviceparser.SchemaValidator;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;

public class SAXParser<T> implements Parser<T> {
    private Handler<T> handler;
    private String schemePath = null;

    public SAXParser(Handler<T> concreteHandler) {
        handler = concreteHandler;
    }

    public SAXParser(Handler<T> concreteHandler, String pathToSchema) {
        handler = concreteHandler;
        schemePath = pathToSchema;
    }


    @Override
    public T parse(String xmlPath) throws IllegalArgumentException {
        if (schemePath != null) {
            if (!SchemaValidator.validate(xmlPath, schemePath)) {
                return null;
            }
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        T parseResult;
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            SAXHandlerProxy<T> saxHandler = new SAXHandlerProxy<T>(handler);
            saxParser.parse(new File(xmlPath), saxHandler);
            parseResult = handler.getParseResult();
        } catch (Exception e) {
            parserLogger.log(Level.ALL, Arrays.toString(e.getStackTrace()));
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
        return parseResult;
    }

    private static class SAXHandlerProxy<T> extends DefaultHandler {
        private T parseResult;
        private Handler<T> handler;

        public SAXHandlerProxy(Handler<T> newHandler) {
            handler = newHandler;
        }

        public T getParseResult() {
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
