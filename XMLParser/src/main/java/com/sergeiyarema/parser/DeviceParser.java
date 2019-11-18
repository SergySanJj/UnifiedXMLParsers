package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;

abstract class DeviceParser {
    protected DeviceHandler handler = new DeviceHandler();
    abstract public Device parse(String pathToXML);
}
