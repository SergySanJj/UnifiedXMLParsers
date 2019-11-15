package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;

interface DeviceParser {
    Device parse(String pathToXML);
}
