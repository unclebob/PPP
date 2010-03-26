package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Media;


public class DamagedCondition extends ReturnCondition {
  public Money getCharge(Media book) {
    if (book instanceof Damageable) {
      Damageable damageable = (Damageable) book;
      return damageable.getDamageCharge();
    } else
      return new Money(0);
  }
}
