package com.objectmentor.library.libraryRules;

import junit.framework.TestCase;

import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.models.Computer;
import com.objectmentor.library.models.Patron;
import com.objectmentor.library.utils.DateUtil;
import com.objectmentor.library.web.controller.Application;
import com.objectmentor.library.web.controller.MockServiceProvider;

public class LibraryBorrowComputerTest extends TestCase {

  private Library library;
  private Patron patron;
  private ComputerGateway computerGateway;
  private Computer knownComputer;

  protected void setUp() throws Exception {
    Application application = new Application(new MockServiceProvider());
    library = application.getLibrary();
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    patron.setId("" + application.getPatronGateway().getNextId());
    application.getPatronGateway().getPatronMap().put(patron.getId(), patron);

    
    String knownMacAddress = "ma:ca:dd:re:ss:01";
    computerGateway = application.getComputerGateway();
    computerGateway.acceptComputer(
      knownMacAddress,
      "library1",
      "C122: HP Pavilian NW corner, Blue sticker"
    );
    knownComputer = computerGateway.findComputerByMacAddress(knownMacAddress);
  }

  public void testCanBorrowKnownComputer() throws Exception {
    ComputerLoanReceipt receipt = library.loanComputer(knownComputer, patron);
    assertEquals(ComputerLoanReceipt.OK, receipt.getStatus());
  }
}
