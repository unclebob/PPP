package com.objectmentor.library.utils;

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

  public void testToString() throws Exception {
    assertEquals("$3.50", new Money(350).toString());
    assertEquals("$75.02", new Money(7502).toString());
    assertEquals("$0.01", new Money(1).toString());
    assertEquals("$0.00", new Money(0).toString());
  }

  public void testTimes() {
    assertEquals(new Money(50).times(5), new Money(250));
  }


}
