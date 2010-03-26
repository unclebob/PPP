package com.objectmentor.library.libraryRules;

import com.objectmentor.library.models.Media;

public class DamagedCondition extends ReturnCondition {
  public Money getCharge(Media media) {
    switch (media.getTypeCode()) {
      case(Media.BOOK):
        return new Money(500);
      case(Media.COMPACT_DISC):
        return new Money(1500);
    }
    return new Money(0);
  }
}
