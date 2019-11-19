package com.sergeiyarema.parser;

import com.sergeiyarema.parser.deviceinfo.Device;
import com.sergeiyarema.parser.deviceparser.DeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.DOMDeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.SAXDeviceParser;
import com.sergeiyarema.parser.deviceparser.realisations.StAXDeviceParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsersTests {
    private String resourcePath = "resources/";
    private Map<String, Boolean> testFiles = Map.of(
            "case", true,
            "mouse", true,
            "reordered", false,
            "unclosed_tag", false,
            "just_device", false
    );
    private Map<String, String> correctDevicesRepresentation = new HashMap<>();

    private List<DeviceParser> parsers = List.of(
            new StAXDeviceParser(),
            new DOMDeviceParser(),
            new SAXDeviceParser()
    );

    public ParsersTests() {
        correctDevicesRepresentation.put("mouse",
                "id: 0000_some_id\n" +
                        "name: Mouse\n" +
                        "id: 150\n" +
                        "type: \n" +
                        "- is pereferial: true\n" +
                        "- energy consumption: 3 WT\n" +
                        "- group: IO\n" +
                        "- ports: USB; \n" +
                        "is critical: false\n");

        correctDevicesRepresentation.put("case",
                "id: 0001_some_id\n" +
                        "name: Energy Case\n" +
                        "id: 120\n" +
                        "type: \n" +
                        "- is pereferial: false\n" +
                        "- energy consumption: 10 WT\n" +
                        "- has coolers: 2\n" +
                        "- group: IO\n" +
                        "- ports: USB; COM; LPT; \n" +
                        "is critical: false\n");
    }


    private String formFilePath(String filename) {
        return resourcePath + filename + ".xml";
    }

    private boolean processErrored(String filename, DeviceParser parser) {
        try {
            Device res = parser.parse(formFilePath(filename));
            res.toString();
            if (res == null)
                return true;
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private String processCorrect(String filename, DeviceParser parser) {
        return parser.parse(formFilePath(filename)).toString();
    }

    @Test
    public void testParsers() {
        for (DeviceParser parser : parsers) {
            for (Map.Entry<String, Boolean> testEntry : testFiles.entrySet()) {
                if (testEntry.getValue()) {
                    String expected = correctDevicesRepresentation.get(testEntry.getKey());
                    Assert.assertEquals(expected, processCorrect(testEntry.getKey(), parser));
                } else {
                    Assert.assertTrue(processErrored(testEntry.getKey(), parser));
                }
            }
        }
    }
}