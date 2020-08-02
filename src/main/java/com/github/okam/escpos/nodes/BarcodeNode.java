package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.BufferBuilder.BARCODE_LABEL_FONT;
import com.github.okam.escpos.BufferBuilder.BARCODE_LABEL_POSITION;
import com.github.okam.escpos.BufferBuilder.BARCODE_SYSTEM;
import com.github.okam.escpos.BufferBuilder.BARCODE_WIDTH;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class BarcodeNode extends XMLNode {
  public BarcodeNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    BARCODE_SYSTEM system =
        BARCODE_SYSTEM.valueOfLabel(attributes.get("system"), BARCODE_SYSTEM.ITF);
    BARCODE_WIDTH width =
        BARCODE_WIDTH.valueOfLabel(attributes.get("width"), BARCODE_WIDTH.DOT_375);
    BARCODE_LABEL_FONT labelFont =
        BARCODE_LABEL_FONT.valueOfLabel(attributes.get("labelFont"), BARCODE_LABEL_FONT.FONT_A);
    BARCODE_LABEL_POSITION labelPosition =
        BARCODE_LABEL_POSITION.valueOfLabel(
            attributes.get("labelPosition"), BARCODE_LABEL_POSITION.BOTTOM);
    byte height = (byte) 162;
    try {
      height = (byte) Integer.parseInt(attributes.get("height"));
    } catch (NumberFormatException e) {
      // ignore
    }

    byte leftSpacing = 0;
    try {
      leftSpacing = (byte) Integer.parseInt(attributes.get("leftSpacing"));
    } catch (NumberFormatException e) {
      // ignore
    }

    if (this.content.length > 0)
      return bufferBuilder.printBarcode(
          this.content, system, width, height, labelFont, labelPosition, leftSpacing);

    return bufferBuilder;
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }
}
