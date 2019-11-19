package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceparser.realisations.DOMParser;
import com.sergeiyarema.parser.deviceparser.realisations.DeviceHandler;
import com.sergeiyarema.parser.deviceparser.realisations.SAXParser;
import com.sergeiyarema.parser.deviceparser.realisations.StAXParser;

import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        Logger mainLogger = Logger.getLogger("main");

        DOMParser<Device> parserDOM = new DOMParser<>(new DeviceHandler());
        mainLogger.log(Level.INFO, parserDOM.parse("resources/mouse.xml").toString());

        SAXParser<Device> parserSAX = new SAXParser<>(new DeviceHandler());
        mainLogger.log(Level.INFO, parserSAX.parse("resources/case.xml").toString());

        StAXParser<Device> parserStAX = new StAXParser<>(new DeviceHandler());
        mainLogger.log(Level.INFO, parserStAX.parse("resources/case.xml").toString());
    }
}
