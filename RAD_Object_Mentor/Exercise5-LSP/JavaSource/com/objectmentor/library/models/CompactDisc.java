package com.objectmentor.library.models;

import com.objectmentor.library.libraryRules.Money;

public class CompactDisc extends Media {

	public CompactDisc(String barCode, String title, String artist) {
    super(barCode, title, artist);
  }
  
  public int howManyDaysThisCanBeBorrowed() {
    return 7;
  }
  
  public Money perDiemFine() {
  	return new Money(150);
  }

  public Money getDamageCharge() {
    return new Money(1500);
  }
  
}
