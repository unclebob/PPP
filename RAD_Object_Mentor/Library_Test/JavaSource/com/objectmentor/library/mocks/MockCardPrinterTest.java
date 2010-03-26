package com.objectmentor.library.mocks;

import com.objectmentor.library.application.models.*;
import junit.framework.TestCase;

public class MockCardPrinterTest extends TestCase {
  private Patron patron;
  private MockCardPrinter cardPrinter;

  public void setUp() {
    Address addy = new Address("12 The Steet", "", "Bedrock", "Prehistoria", "00001-001");
    patron = new Patron("Fred", "F", "Flintstone", addy, "847-555-2231");
    cardPrinter = new MockCardPrinter();
  }

  public void testPrinting() {
    cardPrinter.print(patron);
    assertEquals(patron, cardPrinter.lastPatronPrinted);
  }

}
