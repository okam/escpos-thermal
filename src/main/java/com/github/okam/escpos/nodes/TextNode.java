package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class TextNode extends XMLNode {
  public TextNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    try {
      if (attributes.containsKey("size")) {
        String[] s = attributes.get("size").split(":");
        byte width = (byte) Integer.parseInt(s[0]);
        byte height = (byte) Integer.parseInt(s[1]);
        bufferBuilder.setCharacterSize(width, height);
      }
    } catch (NumberFormatException e) {
      // ignore
    }

    return bufferBuilder.printText(this.content);
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder.resetCharacterSize();
  }
}
