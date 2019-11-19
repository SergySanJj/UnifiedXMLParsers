package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceparser.DeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.DOMDeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.SAXDeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.StAXDeviceParser;

import java.util.logging.*;

public class Main {
    public static void main(String[] args) {
        Logger mainLogger = Logger.getLogger("main");

        DeviceParser StAXparser = new StAXDeviceParser();
        DeviceParser DOMparser = new DOMDeviceParser();

        Device device = StAXparser.parse("resources/mouse.xml");
        mainLogger.log(Level.INFO, device.toString());

        device = DOMparser.parse("resources/case.xml");
        mainLogger.log(Level.INFO, device.toString());

        device = DeviceParser.parse("resources/mouse.xml", new SAXDeviceParser());
        mainLogger.log(Level.INFO, device.toString());

    }
}
