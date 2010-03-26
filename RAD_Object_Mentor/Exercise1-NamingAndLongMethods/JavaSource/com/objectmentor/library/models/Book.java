package com.objectmentor.library.models;

public class Book {
  private BookTitle t;
  private String id;
  private Receipt r;

  public Book(BookTitle t, String string) {
    this.t = t;
    this.id = string;
  }

  public BookTitle getTitle() {
    return t;
  }

  public void setTitle(BookTitle t) {
    this.t = t;
  }

  public boolean isBorrowed() {
    return r != null;
  }

  public String getId() {
    return id;
  }

  public Receipt getReceipt() {
    return r;
  }

  public void setBorrowed(Receipt r) {
    this.r = r;
  }
}
