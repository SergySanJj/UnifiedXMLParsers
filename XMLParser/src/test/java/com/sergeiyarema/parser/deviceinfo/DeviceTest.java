package com.sergeiyarema.parser.deviceinfo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeviceTest {

    @Test
    public void testToString() {
        ArrayList<Port> ports = new ArrayList<>();
        ports.add(Port.COM);
        ports.add(Port.USB);
        Type type = new Type(false, 128, null, Group.IO, ports);
        Device device = new Device("some id", "keyboard", 111, type, false);

        String expected = "id: some id\n" +
                "name: keyboard\n" +
                "id: 111\n" +
                "type: \n" +
                "- is pereferial: false\n" +
                "- energy consumption: 128 WT\n" +
                "- group: IO\n" +
                "- ports: COM; USB; \n" +
                "is critical: false\n";

        Assert.assertEquals(expected,device.toString());
    }
}