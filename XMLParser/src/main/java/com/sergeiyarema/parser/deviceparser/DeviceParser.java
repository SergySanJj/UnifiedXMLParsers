package com.sergeiyarema.parser.deviceparser;

import com.sergeiyarema.parser.deviceinfo.Device;

import java.util.logging.Logger;


public abstract class DeviceParser {
    private static String schemaPath = "resources/device.xsd";
    protected DefaultDeviceHandler handler;
    protected static Logger parserLogger = Logger.getLogger("DeviceParserLogger");

    public DeviceParser() {

    }

    protected abstract Device parseRealisation(String pathToXML);

    // Return parsed device if xml file is correct.
    public Device parse(String pathToXML) {
        if (!SchemaValidator.validate(pathToXML, schemaPath))
            return null;
        prepare();
        return parseRealisation(pathToXML);
    }

    protected void prepare() {
        handler = new DefaultDeviceHandler();
    }

    public static <T extends DeviceParser> Device parse(String pathToXML, T concreteParser) {
        return concreteParser.parse(pathToXML);
    }

    public static String getSchemaPath() {
        return schemaPath;
    }

    public static void setSchemaPath(String schemaPath) {
        DeviceParser.schemaPath = schemaPath;
    }
}
