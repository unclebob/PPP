package com.objectmentor.library.models;

import com.objectmentor.library.utils.Money;

public class Book extends Media {

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
