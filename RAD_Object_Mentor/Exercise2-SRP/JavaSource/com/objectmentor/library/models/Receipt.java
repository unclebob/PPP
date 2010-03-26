package com.objectmentor.library.models;

import com.objectmentor.library.Money;

import java.util.Date;

public class Receipt {

  public static final int OK = 0;
  public static final int UNKNOWN_BOOK = 1;
  public static final int UNBORROWED_BOOK = 2;
  public static final int LATE = 3;

  //Receipt Type
  public static final int BORROW_RECEIPT = 0;
  public static final int RETURN_RECEIPT = 1;

  protected Patron patron;
  protected int type;
  private BookCopy copy;
  private int status;
  private Money fine = new Money(0);

  public Receipt(int type) {
    this.type = type;
  }

  public Receipt(Patron patron, int type) {
    this.patron = patron;
    this.type = type;
  }

  public int getType() {
    return type;
  }

  public BookCopy getCopy() {
    return copy;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setCopy(BookCopy copy) {
    this.copy = copy;
  }

  ///////////////////////////////////////////////////////
  ///////////RETURN RECEIPTS

  public Money getFine() {
    return fine;
  }

  public void setFine(Money fine) {
    this.fine = fine;
  }

  ///////////////////////////////////////////////////////
  ///////////BORROW RECEIPTS
  private Date returnDate;

  public Date getDueDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Patron getBorrower() {
    return patron;
  }

}