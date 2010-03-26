package com.objectmentor.library.models;

import com.objectmentor.library.libraryRules.Money;

public class CompactDisc extends Media {
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
    if (!(getTitle().equals(that.getTitle())))
      return false;
    if (!(this.getAuthor().equals(that.getAuthor())))
      return false;
    return true;
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
