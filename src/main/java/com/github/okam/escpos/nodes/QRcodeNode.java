package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.BufferBuilder.QR_EC_LEVEL;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;

public class QRcodeNode extends XMLNode {
  public QRcodeNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    byte moduleSize = 5;
    try {
      moduleSize = (byte) Integer.parseInt(attributes.get("module_size"));
    } catch (NumberFormatException e) {
      // ignore
    }

    QR_EC_LEVEL ecl = QR_EC_LEVEL.valueOfLabel(attributes.get("ecl"), QR_EC_LEVEL.H);

    if (this.content.length > 0) return bufferBuilder.printQRcode(this.content, moduleSize, ecl);

    return bufferBuilder;
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }
}
