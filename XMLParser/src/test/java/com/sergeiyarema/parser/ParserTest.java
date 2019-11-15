package com.sergeiyarema.parser;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void getOne() {
        Assert.assertEquals(1, Parser.getOne());
    }
}