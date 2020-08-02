package com.github.okam.escpos;

public class Command {
  public static final byte ESC = 0x1B;
  public static final byte FF = 0x0C;
  public static final byte FS = 0x1C;
  public static final byte GS = 0x1D;
  public static final byte DC1 = 0x11;
  public static final byte DC4 = 0x14;
  public static final byte DLE = 0x10;
  public static final byte NL = 0x0A;
  public static final byte SP = 0x20;
  public static final byte US = 0x1F;

  public static byte[] DLE_EOT(byte n) {
    return new byte[] {Command.DLE, 0x04, n};
  } // DLEEOTn

  public static byte[] ESC_init() {
    return new byte[] {Command.ESC, 0x40};
  } // ESC@

  public static byte[] ESC_exclamation(byte n) {
    return new byte[] {Command.ESC, 0x21, n};
  } // ESC!n

  public static byte[] ESC_minus(byte n) {
    return new byte[] {Command.ESC, 0x2D, n};
  } // ESC-n

  public static byte[] ESC_rev(byte n) {
    return new byte[] {Command.ESC, 0x7B, n};
  } // ESC{n

  public static byte[] ESC_a(byte n) {
    return new byte[] {Command.ESC, 0x61, n};
  } // ESCan

  public static byte[] ESC_d(byte n) {
    return new byte[] {Command.ESC, 0x64, n};
  } // ESCdn

  public static byte[] ESC_E(byte n) {
    return new byte[] {Command.ESC, 0x45, n};
  } // ESCEn

  public static byte[] ESC_G(byte n) {
    return new byte[] {Command.ESC, 0x47, n};
  } // ESCGn

  public static byte[] ESC_J(byte n) {
    return new byte[] {Command.ESC, 0x4A, n};
  } // ESCJn

  public static byte[] ESC_M(byte n) {
    return new byte[] {Command.ESC, 0x4D, n};
  } // ESCMn

  public static byte[] ESC_t(byte n) {
    return new byte[] {Command.ESC, 0x07, n};
  } // ESCtn

  public static byte[] ESC_Z(byte m, byte n, byte k) {
    return new byte[] {Command.ESC, 0x5A, m, n, k};
  } // ESCZmnk

  public static byte[] FS_and() {
    return new byte[] {Command.FS, 0x40};
  } // ESC@

  public static byte[] GS_exclamation(byte n) {
    return new byte[] {Command.GS, 0x21, n};
  } // ESC!n

  public static byte[] GS_B(byte n) {
    return new byte[] {Command.GS, 0x42, n};
  } // GSBn

  public static byte[] GS_f(byte n) {
    return new byte[] {Command.GS, 0x66, n};
  } // GSfn

  public static byte[] GS_h(byte n) {
    return new byte[] {Command.GS, 0x68, n};
  } // GShn

  public static byte[] GS_H(byte n) {
    return new byte[] {Command.GS, 0x48, n};
  } // GSHn

  public static byte[] GS_K(byte m, byte n) {
    return new byte[] {Command.GS, 0x6B, m, n};
  } // GSKmn

  public static byte[] GS_v0(byte m) {
    return new byte[] {Command.GS, 0x76, 0x30, m};
  } // GSv0m

  public static byte[] GS_w(byte n) {
    return new byte[] {Command.GS, 0x77, n};
  } // GSwn

  public static byte[] GS_x(byte n) {
    return new byte[] {Command.GS, 0x78, n};
  } // GSxn

  public static byte[] GS_v(byte n) {
    return new byte[] {Command.GS, 0x56, n};
  } // GSv

  public static byte[] LF() {
    return new byte[] {Command.NL};
  }

  // QRCode handling for Gprinter
  public static byte[] K_167(byte n) {
    return new byte[] {Command.GS, 0x28, 0x6B, 0x03, 0x00, 0x31, 0x43, n};
  } // QRCode size: 1 ≤ n ≤ 15 (default 3)

  public static byte[] K_169(byte n) {
    return new byte[] {Command.GS, 0x28, 0x6B, 0x03, 0x00, 0x31, 0x45, n};
  } // Error Correction Level

  public static byte[] K_180(int length) {
    byte pL = (byte) ((length + 3) % 256);
    byte pH = (byte) ((length + 3) / 256);
    return new byte[] {Command.GS, 0x28, 0x6B, pL, pH, 0x31, 0x50, 0x30};
  } // content buffer

  public static byte[] K_181() {
    return new byte[] {Command.GS, 0x28, 0x6B, 0x03, 0x00, 0x31, 0x51, 0x30};
  } // print QRCode

  /* unimplemented
  public static byte[] HT() {return new byte[]{0x09};}
   */
}
