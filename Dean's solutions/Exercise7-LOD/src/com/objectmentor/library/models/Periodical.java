package com.objectmentor.library.models;

import com.objectmentor.library.libraryRules.Money;

public class Periodical extends Media {

  public Periodical(String publicationId, String title, String issue) {
    super(publicationId, makeId(title, issue), title);
  }

  private static String makeId(String title, String issue) {
    return "Periodical:" + title + "/" + issue;
  }

  public int howManyDaysThisCanBeBorrowed() {return 0;}

  public Money getDamageCharge() {
    return new Money(5000);
  }

  public Money perDiemFine() {
    return new Money(2000);
  }
}
