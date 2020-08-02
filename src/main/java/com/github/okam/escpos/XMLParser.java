package com.github.okam.escpos;

import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser {
  static class Handler extends DefaultHandler {
    private XMLNode root = null;
    private Stack<XMLNode> stack = new Stack<>();
    private Stack<StringBuilder> sbStack = new Stack<>();
    private String outputCharset = "UTF-8";

    XMLNode getRoot() {
      return root;
    }

    private Handler(String charsetName) {
      this.outputCharset = outputCharset;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {
      XMLNode xmlNode = NodeFactory.create(NodeFactory.NODE_TYPE.valueOfLabel(qName));
      for (int i = 0; i < attributes.getLength(); i++)
        xmlNode.attributes.put(attributes.getQName(i), attributes.getValue(i));

      if (stack.empty()) this.root = xmlNode;
      else {
        XMLNode parent = stack.peek();
        parent.addChild(xmlNode);
      }

      stack.push(xmlNode);
      sbStack.push(new StringBuilder());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      XMLNode xmlNode = stack.pop();
      StringBuilder sb = sbStack.pop();
      xmlNode.setContent(sb.toString(), this.outputCharset);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
      StringBuilder sb = sbStack.peek();
      if (sb != null) sb.append(new String(ch, start, length));
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(XMLParser.class);

  public static BufferBuilder parse(String xml, String outputCharset) {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    XMLNode root = null;
    BufferBuilder bufferBuilder = null;

    try {
      bufferBuilder = BufferBuilder.newBuilder(true);
      SAXParser saxParser = saxParserFactory.newSAXParser();
      Handler handler = new Handler(outputCharset);
      saxParser.parse(new InputSource(new StringReader(xml)), handler);
      root = handler.getRoot();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      LOGGER.error(e.toString());
    }

    if (root != null) return root.draw(bufferBuilder);
    return bufferBuilder;
  }
}
