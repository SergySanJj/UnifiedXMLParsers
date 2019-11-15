package com.sergeiyarema.parser;

public interface DefaultHandler<T> {
    void onTagStart(String tagName);

    void onTagEnd(String tagName);

    void setAttribute(String attributeName, String value);

    void setTag(String information);

    T getParseResult();
}
