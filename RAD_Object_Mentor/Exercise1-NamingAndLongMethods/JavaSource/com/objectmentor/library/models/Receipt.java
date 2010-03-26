package com.objectmentor.library.models;

import java.util.Date;

public class Receipt {
  public static final int OK = 1;
  public static final int NO_SUCH_BOOK = 2;

  private int stat;
  private Date d;
  private Patron p;
  private Book c;

  public Receipt(Patron p) {
    this.p = p;
  }

  public int getStatus() {
    return stat;
  }

  public void setStatus(int stat) {
    this.stat = stat;
  }

  /**
   * Returns the return date
   * @return
   */
  public Date getDate() {
    return d;
  }

  /**
   * Sets the return date
   * @param d
   */
  public void setDate(Date d) {
    this.d = d;
  }

  /**
   * Returns the patron who borrowed the book
   * @return
   */
  public Patron getPatron() {
    return p;
  }

  /**
   * Returns the exact copy instance that is
   * associated with this specific receipt
   * @return
   */
  public Book getCopy() {
    return c;
  }

  /**
   * Sets the copy
   * @param c
   */
  public void setCopy(Book c) {
    this.c = c;
  }
}
