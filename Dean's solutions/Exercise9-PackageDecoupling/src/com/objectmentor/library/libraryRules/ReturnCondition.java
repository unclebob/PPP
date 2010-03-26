package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.utils.Money;

public abstract class ReturnCondition {
  public String getConditionName() {
    return this.getClass().getName();
  }

  public abstract Money getCharge(Media media);
}
