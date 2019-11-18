package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;


public class Main {
    public static void main(String[] args) {
        StAXDeviceParser StAXparser = new StAXDeviceParser();
        DOMDeviceParser DOMparser = new DOMDeviceParser();
        Device device = StAXparser.parse("resources/mouse.xml");
        System.out.println(device.toString());

        device = null;
        device = DOMparser.parse("resources/case.xml");
        System.out.println(device.toString());
    }
}
