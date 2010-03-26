package com.objectmentor.library.libraryRules;

import java.text.NumberFormat;

public class Money {
  private int pennies;
  public static final Money ZERO = new Money(0);

  public Money(int pennies) {
    this.pennies = pennies;
  }

  public boolean equals(Object object) {
    if (object == null || (!(object instanceof Money)))
      return false;

    Money that = (Money) object;
    return this.pennies == that.pennies;
  }

  public int hashCode() {
    return pennies;
  }

  public String toString() {
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    return nf.format(pennies / 100.0);
  }

  public Money times(int operand) {
    return new Money(pennies * operand);
  }
}
