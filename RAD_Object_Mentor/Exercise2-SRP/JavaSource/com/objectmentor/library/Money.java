package com.objectmentor.library;

public class Money {
  private int pennies;

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
    return "" + pennies;
  }
}
