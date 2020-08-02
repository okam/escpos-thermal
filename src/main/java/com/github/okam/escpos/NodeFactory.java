package com.github.okam.escpos;

import com.github.okam.escpos.nodes.AlignNode;
import com.github.okam.escpos.nodes.BarcodeNode;
import com.github.okam.escpos.nodes.BoldNode;
import com.github.okam.escpos.nodes.BreakLineNode;
import com.github.okam.escpos.nodes.DocumentNode;
import com.github.okam.escpos.nodes.LineFeedNode;
import com.github.okam.escpos.nodes.PaperCutNode;
import com.github.okam.escpos.nodes.QRcodeNode;
import com.github.okam.escpos.nodes.SmallNode;
import com.github.okam.escpos.nodes.TableCellNode;
import com.github.okam.escpos.nodes.TableNode;
import com.github.okam.escpos.nodes.TableRowNode;
import com.github.okam.escpos.nodes.TextLineNode;
import com.github.okam.escpos.nodes.TextNode;
import com.github.okam.escpos.nodes.UnderlineNode;
import com.github.okam.escpos.nodes.WhiteModeNode;
import java.util.HashMap;
import java.util.Map;

public class NodeFactory {
  public enum NODE_TYPE {
    ALIGN("align"),
    BARCODE("barcode"),
    BOLD("bold"),
    BREAK_LINE("break-line"),
    DOCUMENT("document"),
    LINE_FEED("line-feed"),
    QRCODE("qrcode"),
    SMALL("small"),
    TEXT("text"),
    TEXT_LINE("text-line"),
    UNDERLINE("underline"),
    WHITE_MODE("white-mode"),
    PAPER_CUT("paper-cut"),
    TABLE("table"),
    TABLE_ROW("table-row"),
    TABLE_CELL("table-cell");

    private static final Map<String, NODE_TYPE> BY_LABEL = new HashMap<>();

    static {
      for (NODE_TYPE e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final String label;

    private NODE_TYPE(String label) {
      this.label = label;
    }

    public static String labelOf(NODE_TYPE nodeType) {
      return nodeType.label;
    }

    public static NODE_TYPE valueOfLabel(String label) {
      return BY_LABEL.get(label.toLowerCase());
    }
  }

  public static XMLNode create(NODE_TYPE nodeType) {
    switch (nodeType) {
      case ALIGN:
        return new AlignNode();
      case BARCODE:
        return new BarcodeNode();
      case BOLD:
        return new BoldNode();
      case BREAK_LINE:
        return new BreakLineNode();
      case DOCUMENT:
        return new DocumentNode();
      case LINE_FEED:
        return new LineFeedNode();
      case QRCODE:
        return new QRcodeNode();
      case SMALL:
        return new SmallNode();
      case TEXT:
        return new TextNode();
      case TEXT_LINE:
        return new TextLineNode();
      case UNDERLINE:
        return new UnderlineNode();
      case WHITE_MODE:
        return new WhiteModeNode();
      case PAPER_CUT:
        return new PaperCutNode();
      case TABLE:
        return new TableNode();
      case TABLE_ROW:
        return new TableRowNode();
      case TABLE_CELL:
        return new TableCellNode();
      default:
        return null;
    }
  }
}
