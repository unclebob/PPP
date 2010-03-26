package com.objectmentor.library.models;

public class BookCopy {
  private BookTitle title;
  private String id;
  private BorrowedReceipt receipt;

  public BookCopy(BookTitle title, String id) {
    this.title = title;
    this.id = id;
  }

  public BookTitle getBookTitle() {
    return title;
  }

  public void setTitle(BookTitle title) {
    this.title = title;
  }

  public boolean isBorrowed() {
    return receipt != null;
  }

  public String getId() {
    return id;
  }

  public BorrowedReceipt getBorrowReceipt() {
    return receipt;
  }

  public void setBorrowed(BorrowedReceipt receipt) {
    this.receipt = receipt;
  }
}
