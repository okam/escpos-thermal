package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.BufferBuilder.UNDERLINE_MODE;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class UnderlineNode extends XMLNode {
  public UnderlineNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    UNDERLINE_MODE mode =
        UNDERLINE_MODE.valueOfLabel(attributes.get("mode"), UNDERLINE_MODE.TWO_POINTS_OF_COARSE);
    return bufferBuilder.startUnderline(mode);
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.endUnderline();
  }
}
