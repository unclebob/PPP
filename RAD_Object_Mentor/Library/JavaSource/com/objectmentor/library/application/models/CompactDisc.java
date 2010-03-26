package com.objectmentor.library.application.models;

import com.objectmentor.library.application.libraryRules.Damageable;
import com.objectmentor.library.utils.Money;

public class CompactDisc extends Media implements Damageable {
  public String artist;

  public CompactDisc(String barCode, String title, String artist) {
    super(barCode, title, artist);
  }

  public int howManyDaysThisCanBeBorrowed() {
    return 7;
  }

  public Money getDamageCharge() {
    return new Money(1500);
  }

  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (!(obj.getClass() == getClass())) return false;
    CompactDisc that = (CompactDisc) obj;
    return getTitle().equals(that.getTitle()) && getAuthor().equals(that.getAuthor());
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();
    buff.append(super.toString())
      .append(", artist: \"")
      .append(getAuthor())
      .append("\", how many days can it be borrowed? \"")
      .append(howManyDaysThisCanBeBorrowed())
      .append("\"");
    return buff.toString();
  }

  public Money perDiemFine() {
    return new Money(150);
  }
}
