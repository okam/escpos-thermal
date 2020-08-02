package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.BufferBuilder.ALIGNMENT;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableCellNode extends XMLNode {
  static class IntWrapper {
    private int value;

    IntWrapper(int value) {
      this.value = value;
    }
  }

  static class BufferWrapper {
    private byte[] buffer;

    BufferWrapper(int length) {
      this.buffer = new byte[length];
    }
  }

  protected List<byte[]> lines = new ArrayList<>();
  protected ALIGNMENT alignment = ALIGNMENT.LEFT;
  protected byte length = 10;
  protected byte fontWidth = 0;
  protected byte fontHeight = 0;
  protected byte paddingLeft = 0, paddingRight = 0;

  public TableCellNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    try {
      if (attributes.containsKey("align")) {
        alignment = ALIGNMENT.valueOfLabel(attributes.get("align"), ALIGNMENT.LEFT);
      }
      if (attributes.containsKey("length")) {
        length = (byte) Integer.parseInt(attributes.get("length"));
      }
      if (attributes.containsKey("size")) {
        String[] s = attributes.get("size").split(":");
        fontWidth = (byte) Integer.parseInt(s[0]);
        fontHeight = (byte) Integer.parseInt(s[1]);
      }
      if (attributes.containsKey("padding")) {
        String[] s = attributes.get("padding").split(":");
        if (s.length == 2) {
          paddingLeft = (byte) Integer.parseInt(s[0]);
          paddingRight = (byte) Integer.parseInt(s[1]);
        }
      }
    } catch (NumberFormatException e) {
      // ignore
    }

    IntWrapper offset = new IntWrapper(0);
    while (true) {
      BufferWrapper bufferWrapper = new BufferWrapper(this.length);
      // read line
      int len = subContentWithLength(this.content, offset, this.length, bufferWrapper);

      // fill space
      int paddingLeft = this.paddingLeft;
      if (len < this.length) {
        switch (this.alignment) {
          case RIGHT:
            paddingLeft += this.length - len;
            break;
          case CENTER:
            paddingLeft += (this.length - len) / 2;
            break;
        }
      }

      int length = this.length + this.paddingLeft + this.paddingRight;
      byte[] buffer = new byte[length];
      // init line buffer
      Arrays.fill(buffer, (byte) 0x20);
      // assign content
      System.arraycopy(bufferWrapper.buffer, 0, buffer, paddingLeft, len);

      // append new line
      lines.add(buffer);
      // break if no more content
      if (offset.value >= content.length) break;
    }

    return bufferBuilder;
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }

  private int subContentWithLength(
      byte[] content, IntWrapper offset, int length, BufferWrapper bufferWrapper) {
    int len = 0;
    int idx = offset.value;

    while (true) {
      short tmpst = (short) (content[idx] & 0xF0);
      if (tmpst >= 0xB0) {
        // preventing half character
        if (length - len < 2) break;

        if (tmpst < 0xC0) {
          idx += 2;
          len += 2;
        } else if ((tmpst == 0xC0) || (tmpst == 0xD0)) {
          idx += 2;
          len += 2;
        } else if (tmpst == 0xE0) {
          idx += 3;
          len += 2;
        } else if (tmpst == 0xF0) {
          short tmpst0 = (short) (content[idx] & 0x0F);
          if (tmpst0 == 0) {
            idx += 4;
            len += 2;
          } else if ((tmpst0 > 0) && (tmpst0 < 12)) {
            idx += 5;
            len += 2;
          } else if (tmpst0 > 11) {
            idx += 6;
            len += 2;
          }
        }
      } else {
        idx++;
        len++;
      }

      if ((idx >= content.length) || (len >= length)) {
        break;
      }
    }

    System.arraycopy(content, offset.value, bufferWrapper.buffer, 0, idx - offset.value);
    offset.value = idx;
    return len;
  }
}
