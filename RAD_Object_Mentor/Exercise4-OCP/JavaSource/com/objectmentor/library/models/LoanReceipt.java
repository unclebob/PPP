package com.objectmentor.library.models;

import java.util.Date;

public class LoanReceipt {
  public static final int OK = 1;
  public static final int NO_SUCH_MEDIA = 2;
  public static final int ALREADY_BORROWED = 3;
  public static final int DISALLOWED = 4;
  public static final int NO_SUCH_PATRON = 5;

  private int status;
  private Date returnDate;
  private Patron borrower;
  private MediaCopy borrowedCopy;

  public LoanReceipt(Patron borrower) {
    this.borrower = borrower;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getDueDate() {
    return returnDate;
  }

  public void setDueDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Patron getBorrower() {
    return borrower;
  }

  public MediaCopy getBorrowedCopy() {
    return borrowedCopy;
  }

  public void setLoanedCopy(MediaCopy borrowedCopy) {
    this.borrowedCopy = borrowedCopy;
  }
}
