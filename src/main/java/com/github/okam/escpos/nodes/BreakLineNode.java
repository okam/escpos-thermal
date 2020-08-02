package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class BreakLineNode extends XMLNode {
  public BreakLineNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    byte lines = 0;
    try {
      lines = (byte) (Integer.parseInt(attributes.get("lines")) - 1);
    } catch (NumberFormatException e) {
      // ignore
    }
    return bufferBuilder.breakLine(lines);
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }
}
