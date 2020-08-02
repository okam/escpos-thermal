package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class TextLineNode extends XMLNode {
  private TextNode textNode;

  public TextLineNode() {
    super();
    this.textNode = new TextNode();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    this.textNode.setContent(this.content);
    return this.textNode.open(bufferBuilder);
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return this.textNode.close(bufferBuilder).breakLine((byte) 0);
  }
}
