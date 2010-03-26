package com.objectmentor.library.libraryRules;

import com.objectmentor.library.application.Application;
import com.objectmentor.library.gateways.ComputerGateway;
import com.objectmentor.library.models.*;
import com.objectmentor.library.utils.DateUtil;
import com.objectmentor.library.web.controller.MockServiceProvider;
import junit.framework.TestCase;

public class LibraryBorrowComputerTest extends TestCase {

  private Library library;
  private Patron patron;
  private ComputerGateway computerGateway;
  private Computer knownComputer;

  protected void setUp() throws Exception {
    Application application = new Application(new MockServiceProvider());
    library = application.getLibrary();
    patron = new Patron(DateUtil.dateFromString("1/1/2000"));
    application.getPatronGateway().add(patron);
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

//  public void testComputerMustBeAvailable() throws Exception {
//    
//    Patron anotherPatron = new Patron("patron2", new Date());
//    library.getPatronGateway().add(anotherPatron);
//    library.loanComputer(knownComputer.getId(), anotherPatron.getId());
//    
//    ComputerLoanReceipt receipt = library.loanComputer(
//        knownComputer.getId(), 
//        patron.getId()
//        );
//    assertEquals(ComputerLoanReceipt.COMPUTER_NOT_AVAILABLE, receipt.getStatus());
//  }

}
