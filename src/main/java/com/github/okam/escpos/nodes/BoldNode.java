package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class BoldNode extends XMLNode {
  public BoldNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.startBold();
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.endBold();
  }
}
