package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {
  public void testEqualityShouldPassWithEqualValues() throws Exception {
    assertEquals(new Money(0), new Money(0));
  }

  public void testEqualityShouldFailWithUnequalValues() throws Exception {
    assertFalse(new Money(0).equals(new Money(1)));
  }

  public void testEqualityShouldFailOnNull() {
    Money money = null;
    assertFalse(new Money(0).equals(money));
  }

  public void testEqualityShouldFailOnWrongType() {
    assertFalse(new Money(0).equals("I am not money"));
  }

}
