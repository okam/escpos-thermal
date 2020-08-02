package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class SmallNode extends XMLNode {
  public SmallNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.startCompressedCharacter();
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.endCompressedCharacter();
  }
}
