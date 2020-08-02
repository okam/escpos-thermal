package com.github.okam.escpos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BufferBuilder {
  public enum UNDERLINE_MODE {
    ONE_POINT_OF_COARSE((byte) 49, "one-point"),
    TWO_POINTS_OF_COARSE((byte) 50, "two-points");

    private static final Map<String, UNDERLINE_MODE> BY_LABEL = new HashMap<>();

    static {
      for (UNDERLINE_MODE e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private UNDERLINE_MODE(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(UNDERLINE_MODE mode) {
      return mode.value;
    }

    public static UNDERLINE_MODE valueOfLabel(String label, UNDERLINE_MODE defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum ALIGNMENT {
    LEFT((byte) 48, "left"),
    CENTER((byte) 49, "center"),
    RIGHT((byte) 50, "right");

    private final byte value;
    private final String label;
    private static final Map<String, ALIGNMENT> BY_LABEL = new HashMap<>();

    static {
      for (ALIGNMENT e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private ALIGNMENT(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(ALIGNMENT mode) {
      return mode.value;
    }

    public static ALIGNMENT valueOfLabel(String label, ALIGNMENT defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum BARCODE_SYSTEM {
    UPC_A((byte) 65, "upc_a"),
    UPC_E((byte) 66, "upc_e"),
    EAN_13((byte) 67, "ean_13"),
    EAN_8((byte) 68, "ean_8"),
    CODE_39((byte) 69, "code_39"),
    ITF((byte) 70, "itf"),
    CODABAR((byte) 71, "codabar"),
    CODE_93((byte) 72, "code_93"),
    CODE_128((byte) 73, "code_128");

    private static final Map<String, BARCODE_SYSTEM> BY_LABEL = new HashMap<>();

    static {
      for (BARCODE_SYSTEM e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private BARCODE_SYSTEM(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(BARCODE_SYSTEM mode) {
      return mode.value;
    }

    public static BARCODE_SYSTEM valueOfLabel(String label, BARCODE_SYSTEM defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum BARCODE_WIDTH {
    DOT_250((byte) 2, "dot_250"),
    DOT_375((byte) 3, "dot_375"),
    DOT_560((byte) 4, "dot_560"),
    DOT_625((byte) 5, "dot_625"),
    DOT_750((byte) 6, "dot_750");

    private static final Map<String, BARCODE_WIDTH> BY_LABEL = new HashMap<>();

    static {
      for (BARCODE_WIDTH e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private BARCODE_WIDTH(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(BARCODE_WIDTH mode) {
      return mode.value;
    }

    public static BARCODE_WIDTH valueOfLabel(String label, BARCODE_WIDTH defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum BARCODE_LABEL_FONT {
    FONT_A((byte) 48, "font_a"),
    FONT_B((byte) 49, "font_b");

    private static final Map<String, BARCODE_LABEL_FONT> BY_LABEL = new HashMap<>();

    static {
      for (BARCODE_LABEL_FONT e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private BARCODE_LABEL_FONT(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(BARCODE_LABEL_FONT mode) {
      return mode.value;
    }

    public static BARCODE_LABEL_FONT valueOfLabel(String label, BARCODE_LABEL_FONT defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum BARCODE_LABEL_POSITION {
    NOT_PRINT((byte) 48, "not_print"),
    ABOVE((byte) 49, "above"),
    BOTTOM((byte) 50, "bottom"),
    ABOVE_BOTTOM((byte) 51, "above_bottom");

    private static final Map<String, BARCODE_LABEL_POSITION> BY_LABEL = new HashMap<>();

    static {
      for (BARCODE_LABEL_POSITION e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private BARCODE_LABEL_POSITION(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(BARCODE_LABEL_POSITION mode) {
      return mode.value;
    }

    public static BARCODE_LABEL_POSITION valueOfLabel(
        String label, BARCODE_LABEL_POSITION defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum QR_EC_LEVEL {
    L((byte) 48, "l"),
    M((byte) 49, "m"),
    Q((byte) 50, "q"),
    H((byte) 51, "h");

    private static final Map<String, QR_EC_LEVEL> BY_LABEL = new HashMap<>();

    static {
      for (QR_EC_LEVEL e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private QR_EC_LEVEL(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(QR_EC_LEVEL mode) {
      return mode.value;
    }

    public static QR_EC_LEVEL valueOfLabel(String label, QR_EC_LEVEL defaultValue) {
      if (BY_LABEL.containsKey(label)) return BY_LABEL.get(label.toLowerCase());
      else return defaultValue;
    }
  }

  public enum BITMAP_SCALE {
    NORMAL((byte) 48, "normal"),
    DOUBLE_WIDTH((byte) 49, "double_width"),
    DOUBLE_HEIGHT((byte) 50, "double_height"),
    FOUR_TIMES((byte) 51, "four_times");

    private static final Map<String, BITMAP_SCALE> BY_LABEL = new HashMap<>();

    static {
      for (BITMAP_SCALE e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private BITMAP_SCALE(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(BITMAP_SCALE mode) {
      return mode.value;
    }

    public static BITMAP_SCALE valueOfLabel(String label) {
      return BY_LABEL.get(label.toLowerCase());
    }
  }

  public enum STATUS_TYPE {
    PRINTER_STATUS((byte) 1, "printer"),
    OFFLINE_STATUS((byte) 2, "offline"),
    ERROR_STATUS((byte) 3, "error"),
    PAPER_ROLL_SENSOR_STATUS((byte) 4, "paper_roll_sensor");

    private static final Map<String, STATUS_TYPE> BY_LABEL = new HashMap<>();

    static {
      for (STATUS_TYPE e : values()) {
        BY_LABEL.put(e.label, e);
      }
    }

    private final byte value;
    private final String label;

    private STATUS_TYPE(byte n, String label) {
      value = n;
      this.label = label;
    }

    public static byte valueOf(STATUS_TYPE mode) {
      return mode.value;
    }

    public static STATUS_TYPE valueOfLabel(String label) {
      return BY_LABEL.get(label.toLowerCase());
    }
  }

  private ByteArrayOutputStream buffer;
  private boolean useDefaultSetting;

  private BufferBuilder(boolean useDefaultSettings) throws IOException {
    this.useDefaultSetting = useDefaultSettings;
    this.buffer = new ByteArrayOutputStream();

    if (useDefaultSettings) {
      resetCharacterSize();
      resetCharacterCodeTable();
    }
  }

  public static BufferBuilder newBuilder(boolean useDefaultSettings) throws IOException {
    BufferBuilder builder = new BufferBuilder(useDefaultSettings);
    return builder;
  }

  public BufferBuilder end() {
    return this;
  }

  public BufferBuilder resetCharacterCodeTable() throws IOException {
    this.buffer.write(Command.ESC_t((byte) 0));
    return this;
  }

  public BufferBuilder setCharacterSize(byte width, byte height) throws IOException {
    byte size = (byte) ((width << 4) + height);
    this.buffer.write(Command.GS_exclamation(size));
    return this;
  }

  public BufferBuilder resetCharacterSize() throws IOException {
    this.buffer.write(Command.GS_exclamation((byte) 0));
    return this;
  }

  public BufferBuilder startCompressedCharacter() throws IOException {
    this.buffer.write(Command.ESC_M((byte) 1));
    return this;
  }

  public BufferBuilder endCompressedCharacter() throws IOException {
    this.buffer.write(Command.ESC_M((byte) 0));
    return this;
  }

  public BufferBuilder startBold() throws IOException {
    this.buffer.write(Command.ESC_E((byte) 1));
    return this;
  }

  public BufferBuilder endBold() throws IOException {
    this.buffer.write(Command.ESC_E((byte) 0));
    return this;
  }

  // UNDERLINE_MODE.TWO_POINTS_OF_COARSE by default
  public BufferBuilder startUnderline(UNDERLINE_MODE underlineMode) throws IOException {
    this.buffer.write(Command.ESC_minus(underlineMode.value));
    return this;
  }

  public BufferBuilder endUnderline() throws IOException {
    this.buffer.write(Command.ESC_minus((byte) 48));
    return this;
  }

  public BufferBuilder startAlign(ALIGNMENT alignment) throws IOException {
    this.buffer.write(Command.ESC_a(alignment.value));
    return this;
  }

  public BufferBuilder resetAlign() throws IOException {
    return this.startAlign(ALIGNMENT.LEFT);
  }

  public BufferBuilder startWhiteMode() throws IOException {
    this.buffer.write(Command.GS_B((byte) 1));
    return this;
  }

  public BufferBuilder endWhiteMode() throws IOException {
    this.buffer.write(Command.GS_B((byte) 0));
    return this;
  }

  public BufferBuilder startReverseMode() throws IOException {
    this.buffer.write(Command.ESC_rev((byte) 1));
    return this;
  }

  public BufferBuilder endReverseMode() throws IOException {
    this.buffer.write(Command.ESC_rev((byte) 0));
    return this;
  }

  // width: BARCODE_WIDTH.DOT_375
  // height: 162
  // labelFont: BARCODE_LABEL_FONT.FONT_A
  // labelPosition: BARCODE_LABEL_POSITION.BOTTOM
  // leftSpacing: 0
  public BufferBuilder printBarcode(
      byte[] data,
      BARCODE_SYSTEM barcodeSystem,
      BARCODE_WIDTH width,
      byte height,
      BARCODE_LABEL_FONT labelFont,
      BARCODE_LABEL_POSITION labelPosition,
      byte leftSpacing)
      throws IOException {
    this.buffer.write(Command.GS_w(width.value)); // width
    this.buffer.write(Command.GS_h(height)); // height
    this.buffer.write(Command.GS_x(leftSpacing)); // left spacing
    this.buffer.write(Command.GS_f(labelFont.value)); // HRI font
    this.buffer.write(Command.GS_H(labelPosition.value)); // HRI font
    this.buffer.write(
        Command.GS_K(barcodeSystem.value, (byte) data.length)); // data is a string in UTF-8
    this.buffer.write(data);
    return this;
  }

  // version: 1
  // errorCorrectionLevel: QR_EC_LEVEL.H
  // size: 5
  public BufferBuilder printQRcode(byte[] data, byte size, QR_EC_LEVEL errorCorrectionLevel)
      throws IOException {
    // step 1: set QRCode size
    this.buffer.write(Command.K_167(size));
    // step 2: set error correction level
    this.buffer.write(Command.K_169(errorCorrectionLevel.value));
    // step 3: set buffer length
    this.buffer.write(Command.K_180(data.length));
    // step 4: send content
    this.buffer.write(data);
    // step 5: print
    this.buffer.write(Command.K_181());
    return this;
  }

  // scale: BITMAP_SCALE.NORMAL
  public BufferBuilder printBitmap(byte[] image, int width, int height, BITMAP_SCALE scale)
      throws IOException {
    // TODO
    return this;
  }

  public BufferBuilder printText(byte[] data) throws IOException {
    this.buffer.write(data);
    return this;
  }

  public BufferBuilder printTextLine(byte[] data) throws IOException {
    return this.printText(data).breakLine((byte) 0);
  }

  // lines: 0
  public BufferBuilder breakLine(byte lines) throws IOException {
    this.buffer.write(Command.ESC_d(lines));
    return this;
  }

  public BufferBuilder lineFeed() throws IOException {
    this.buffer.write(Command.LF());
    return this;
  }

  public BufferBuilder transmitStatus(STATUS_TYPE statusType) throws IOException {
    this.buffer.write(Command.DLE_EOT(statusType.value));
    return this;
  }

  public byte[] build() throws IOException {
    if (this.useDefaultSetting) {
      this.lineFeed();
      this.buffer.write(Command.ESC_init());
    }

    return this.buffer.toByteArray();
  }

  /**
   * Register Paper Cut Action
   *
   * @return BufferBuilder
   */
  public BufferBuilder paperCut() throws IOException {
    this.buffer.write(Command.GS_v((byte) 1));
    return this;
  }
}
