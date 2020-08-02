package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class TableNode extends XMLNode {
  public TableNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    for (XMLNode row : this.children) {
      if (row instanceof TableRowNode) ((TableRowNode) row).printRow(bufferBuilder);
    }
    return bufferBuilder;
  }
}
