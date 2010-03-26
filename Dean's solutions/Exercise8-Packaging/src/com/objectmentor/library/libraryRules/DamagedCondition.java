package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Media;

public class DamagedCondition extends ReturnCondition {
  public Money getCharge(Media media) {
    if (media instanceof Damageable) {
      Damageable damageable = (Damageable) media;
      return damageable.getDamageCharge();
    } else
      return new Money(0);
  }
}
