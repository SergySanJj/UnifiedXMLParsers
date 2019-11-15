package com.sergeiyarema.parser;


import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceinfo.Group;
import com.sergeiyarema.parser.deviceinfo.Port;
import com.sergeiyarema.parser.deviceinfo.Type;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StAXDeviceParser parser = new StAXDeviceParser();
        Device device = parser.parse("resources/mouse.xml");

        System.out.println(device.toString());
    }
}
