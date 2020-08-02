package com.github.okam.escpos;

import java.util.ArrayList;
import java.util.List;

public class TestTemplateModel {
  public static class Item {
    private String name;
    private String quantity;
    private String price;
    private final String padding = "";

    Item(String name, String quantity, String price) {
      this.name = name;
      this.quantity = quantity;
      this.price = price;
    }

    public String name() {
      return name + padding;
    }

    public String quantity() {
      return quantity + padding;
    }

    public String price() {
      return price + padding;
    }
  }

  public TestTemplateModel() {}

  public String title() {
    return "Delicious Restaurant";
  }

  public String subtitle() {
    return "Deliver Food Everywhere";
  }

  public long date() {
    return 0L;
  }

  public String note() {
    return "Note: no pepper";
  }

  public String description() {
    return "Something Cool";
  }

  public List<Item> items() {
    List<Item> items = new ArrayList<>();
    items.add(
        new Item("Loooooooooooooooooooooooooooooooooooooooooooooooooog Noodle", "1", "10.00"));
    items.add(new Item("Many Sausages", "1999", "5.00"));
    items.add(new Item("Expensive Juice", "2", "1000000.00"));
    return items;
  }

  public String qrcode() {
    return "Love It";
  }
}
