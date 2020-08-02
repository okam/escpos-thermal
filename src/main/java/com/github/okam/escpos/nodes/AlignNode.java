package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.BufferBuilder.ALIGNMENT;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class AlignNode extends XMLNode {
  public AlignNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    ALIGNMENT alignment = ALIGNMENT.valueOfLabel(attributes.get("mode"), ALIGNMENT.LEFT);
    return bufferBuilder.startAlign(alignment);
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.resetAlign();
  }
}
