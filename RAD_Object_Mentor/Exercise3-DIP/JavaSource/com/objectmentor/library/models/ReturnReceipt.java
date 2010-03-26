package com.objectmentor.library.models;

import java.util.HashMap;
import java.util.Map;

import com.objectmentor.library.libraryRules.Money;
import com.objectmentor.library.libraryRules.ReturnCondition;

public class ReturnReceipt {
  private MediaCopy copy;
  public static final int OK = 0;
  public static final int UNBORROWED_BOOK = 1;
  public static final int LATE = 2;
  private int status = OK;
  private Money fine = new Money(0);
  private Map<String, Money> charges = new HashMap<String, Money>();

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

  public Map<String, Money> getCharges() {
    return charges;
  }

  public void addCharge(ReturnCondition condition, Money charge) {
    charges.put(condition.getConditionName(), charge);
  }
}
