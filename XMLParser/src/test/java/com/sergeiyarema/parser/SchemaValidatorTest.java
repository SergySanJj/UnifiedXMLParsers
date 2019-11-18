package com.sergeiyarema.parser;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchemaValidatorTest {

    @Test
    public void validateCorrectFile() {
        Assert.assertTrue(SchemaValidator.validate("resources/case.xml", "resources/device.xsd"));
        Assert.assertTrue(SchemaValidator.validate("resources/mouse.xml", "resources/device.xsd"));
    }

    @Test
    public void validateIncorrectFile() {
        Assert.assertFalse(SchemaValidator.validate("resources/unclosed_tag.xml", "resources/device.xsd"));
        Assert.assertFalse(SchemaValidator.validate("resources/just_device.xml", "resources/device.xsd"));
        Assert.assertTrue(SchemaValidator.validate("resources/reordered.xml", "resources/device.xsd"));
    }
}