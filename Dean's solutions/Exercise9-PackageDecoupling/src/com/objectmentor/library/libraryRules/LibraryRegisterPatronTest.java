package com.objectmentor.library.libraryRules;

import com.objectmentor.library.application.PatronRegistrar;
import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

public class LibraryRegisterPatronTest extends TestCase {
  private PatronRegistrar patronRegistrar;
  private Patron tim;
  private MockPatronGateway patronGateway;
  private MockCardPrinter mockCardPrinter;

  protected void setUp() throws Exception {
    patronGateway = new MockPatronGateway();
    mockCardPrinter = new MockCardPrinter();
    patronRegistrar = new PatronRegistrar(patronGateway, mockCardPrinter);
    tim = new Patron(DateUtil.dateFromString("1/1/2000"));
  }

  public void testShouldAddToPatronGateway() {
    patronRegistrar.registerPatron(tim);
    assertSame(tim, patronGateway.findById(tim.getId()));
  }

  public void testShouldPrintCard() {
    patronRegistrar.registerPatron(tim);
    assertSame(tim, mockCardPrinter.lastPatronPrinted);
  }
}
