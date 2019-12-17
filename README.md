# XMLParser

Unified interface for DOM, SAX, StAX parsers.

All you need to do is implement concrete class of <a href="XMLParser/src/main/java/com/sergeiyarema/parser/deviceparser/realisations/Handler.java">Handler</a> interface and then pass it to parser like:

```Java
// DeviceHandler implements Handler<Device>
DOMParser<Device> parserDOM = new DOMParser<>(new DeviceHandler());
parserDOM.parse("resources/mouse.xml").toString();

```
