package com.sergeiyarema.parser;


import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceinfo.Group;
import com.sergeiyarema.parser.deviceinfo.Port;
import com.sergeiyarema.parser.deviceinfo.Type;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Port> ports = new ArrayList<>();
        ports.add(Port.COM);
        ports.add(Port.USB);
        Type type = new Type(false, 128, true, Group.IO, ports);
        Device device = new Device("some id", "keyboard", 111, type, false);

        System.out.println(device.toString());
    }
}
