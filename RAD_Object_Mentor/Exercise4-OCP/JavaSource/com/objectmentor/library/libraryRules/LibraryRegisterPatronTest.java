package com.objectmentor.library.libraryRules;

import com.objectmentor.library.mocks.*;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;
import junit.framework.TestCase;

public class LibraryRegisterPatronTest extends TestCase {
  private Library library;
  private Patron tim;
  private MockPatronGateway patronGateway;
  private MockCardPrinter mockCardPrinter;

  protected void setUp() throws Exception {
    patronGateway = new MockPatronGateway();
    mockCardPrinter = new MockCardPrinter();
    library = new Library(new MockIsbnService(), null, new MockMediaGateway(),
                          patronGateway, mockCardPrinter, null);
    tim = new Patron(DateUtil.dateFromString("1/1/2000"));
  }

  public void testShouldAddToPatronGateway() {
    library.registerPatron(tim);
    assertSame(tim, patronGateway.findById(tim.getId()));
  }

  public void testShouldPrintCard() {
    library.registerPatron(tim);
    assertSame(tim, mockCardPrinter.lastPatronPrinted);
  }
}
