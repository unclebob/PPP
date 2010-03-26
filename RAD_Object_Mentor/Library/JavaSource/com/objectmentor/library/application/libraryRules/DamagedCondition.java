package com.objectmentor.library.application.libraryRules;

import com.objectmentor.library.application.models.Media;
import com.objectmentor.library.utils.Money;

public class DamagedCondition extends ReturnCondition {
  public Money getCharge(Media media) {
    if (media instanceof Damageable) {
      Damageable damageable = (Damageable) media;
      return damageable.getDamageCharge();
    } else
      return new Money(0);
  }
}
