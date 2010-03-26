package com.objectmentor.library.models;

import com.objectmentor.library.libraryRules.*;

import java.util.*;

public class ReturnReceipt {
  private MediaCopy copy;
  public static final int OK = 0;
  public static final int UNKNOWN_BOOK = 1;
  public static final int UNBORROWED_BOOK = 2;
  public static final int LATE = 3;
  private int status = OK;
  private Money fine = new Money(0);
  private Map charges = new HashMap();

  public ReturnReceipt() {
  }

  public Money getFines() {
    return fine;
  }

  public MediaCopy getCopy() {
    return copy;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setCopy(MediaCopy copy) {
    this.copy = copy;
  }

  public static int getDaysLate() {
    return 0;
  }

  public void setFine(Money fine) {
    this.fine = fine;
  }

  public Map getCharges() {
    return charges;
  }

  public void addCharge(ReturnCondition condition, Money charge) {
    charges.put(condition.getConditionName(), charge);
  }
}
