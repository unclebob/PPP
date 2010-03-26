package com.objectmentor.library.application.models;

import com.objectmentor.library.application.libraryRules.Damageable;
import com.objectmentor.library.utils.Money;

public class Book extends Media implements Damageable {

  public Book(String isbn, String title, String author) {
    super(isbn, title, author);
  }

  public int howManyDaysThisCanBeBorrowed() {return 14;}

  public Money getDamageCharge() {
    return new Money(500);
  }

  public Money perDiemFine() {
    return new Money(50);
  }
}
