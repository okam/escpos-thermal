package com.github.okam.escpos.nodes;

import com.github.okam.escpos.BufferBuilder;
import com.github.okam.escpos.XMLNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableRowNode extends XMLNode {
  private List<List<String>> lines = new ArrayList<>();
  protected byte paddingTop = 0, paddingBottom = 0;

  public TableRowNode() {
    super();
  }

  @Override
  public BufferBuilder open(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }

  @Override
  public BufferBuilder close(BufferBuilder bufferBuilder) throws IOException {
    return bufferBuilder;
  }

  public BufferBuilder printRow(BufferBuilder bufferBuilder) throws IOException {
    int lineNum = 0;
    boolean finished = true;

    try {
      if (attributes.containsKey("padding")) {
        String[] s = attributes.get("padding").split(":");
        if (s.length == 2) {
          paddingTop = (byte) Integer.parseInt(s[0]);
          paddingBottom = (byte) Integer.parseInt(s[1]);
        }
      }
    } catch (NumberFormatException e) {
      // ignore
    }

    if (paddingTop > 0) bufferBuilder.breakLine(paddingTop);

    do {
      finished = true;

      for (XMLNode node : this.children) {
        if (!(node instanceof TableCellNode)) continue;

        TableCellNode cell = (TableCellNode) node;
        // begin
        // set font size
        if (cell.fontWidth != 0 || cell.fontHeight != 0)
          bufferBuilder.setCharacterSize(cell.fontWidth, cell.fontHeight);

        // print text
        byte[] line = new byte[cell.length + cell.paddingLeft + cell.paddingRight];
        if (cell.lines.size() <= lineNum) {
          // fill empty line
          Arrays.fill(line, (byte) 0x20);
        } else {
          line = cell.lines.get(lineNum);
        }
        bufferBuilder.printText(line);

        // end
        bufferBuilder.resetCharacterSize();

        // not finished if any column still have more line(s)
        if (cell.lines.size() > lineNum + 1) finished = false;
      }
      lineNum++;
    } while (!finished);

    if (paddingBottom > 0) bufferBuilder.breakLine(paddingBottom);

    return bufferBuilder.lineFeed();
  }
}
