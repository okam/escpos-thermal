package com.github.okam.escpos;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XMLNode {
  private static final Logger LOGGER = LoggerFactory.getLogger(XMLNode.class);

  protected Map<String, String> attributes;
  protected byte[] content;
  protected List<XMLNode> children;

  protected XMLNode() {
    this.attributes = new HashMap<>();
    this.children = new ArrayList<>();
  }

  public void addChild(XMLNode child) {
    Preconditions.checkNotNull(child);
    this.children.add(child);
  }

  protected byte[] getContent() {
    return this.content;
  }

  public void setContent(String content, String outputCharset) {
    try {
      this.content = content.getBytes(outputCharset);
    } catch (UnsupportedEncodingException e) {
      LOGGER.error(e.toString());
    }
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public abstract BufferBuilder open(BufferBuilder bufferBuilder) throws IOException;

  public abstract BufferBuilder close(BufferBuilder bufferBuilder) throws IOException;

  public BufferBuilder draw(BufferBuilder bufferBuilder) {
    try {
      // open tag
      this.open(bufferBuilder);
      this.children.forEach(child -> child.draw(bufferBuilder));

      // close tag
      this.close(bufferBuilder);
    } catch (IOException e) {
      LOGGER.error(e.toString());
    }

    return bufferBuilder;
  }
}
